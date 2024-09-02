package com.oasis.hworld.payment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.oasis.hworld.cart.dto.CartDetailDTO;
import com.oasis.hworld.cart.dto.CartOrderDTO;
import com.oasis.hworld.cart.mapper.CartMapper;
import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.common.exception.ErrorCode;
import com.oasis.hworld.deliveryaddress.domain.DeliveryAddress;
import com.oasis.hworld.deliveryaddress.mapper.DeliveryAddressMapper;
import com.oasis.hworld.payment.domain.Order;
import com.oasis.hworld.payment.domain.OrderItem;
import com.oasis.hworld.payment.dto.ConfirmPaymentRequestDTO;
import com.oasis.hworld.payment.dto.OrderRequestDTO;
import com.oasis.hworld.payment.dto.OrderResponseDTO;
import com.oasis.hworld.payment.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.oasis.hworld.common.exception.ErrorCode.*;

/**
 * 결제 서비스 구현체
 * @author 조영욱
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	조영욱        최초 생성
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final DeliveryAddressMapper deliveryAddressMapper;
    private final CartMapper cartMapper;

    @Value("${PAYMENT_SECRET_KEY}")
    private String secretKey;
    @Value("${PAYMENT_CONFIRM_URL}")
    private String confirmUrl;

    /**
     * 주문 생성
     *
     * @author 조영욱
     * @apiNote 주문서 생성 요청 시 주문을 생성한다.
     */
    @Override
    @Transactional
    public OrderResponseDTO addOrder(OrderRequestDTO dto, int memberId) {
        int deliveryAddressId = dto.getDeliveryAddressId();
        int pointUsage = dto.getPointUsage();

        // Request로 들어온 배송지 조회
        DeliveryAddress deliveryAddress = deliveryAddressMapper.selectDeliveryAddressByDeliveryAddressId(deliveryAddressId);
        // 배송지가 존재하지 않거나, 배송지가 본인 것이 아니면 예외
        if (deliveryAddress == null || deliveryAddress.getMemberId() != memberId) {
            throw new CustomException(DELIVERY_ADDRESS_NOT_EXIST);
        }

        // Request로 들어온 장바구니 조회
        List<Integer> cartIdList = dto.getCartIdList();
        List<CartOrderDTO> cartList = cartMapper.selectCartByCartIdList(cartIdList);

        // cartList 비어있다면 예외
        if (cartList.isEmpty()) {
            throw new CustomException(ErrorCode.CART_ITEM_NOT_VALID);
        }

        int priceBeforeDiscount = 0;
        int totalItemCount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartOrderDTO cart : cartList) {
            // cart의 memberId와 현재 요청자가 다르면 예외
            if (cart.getMemberId() != memberId) {
                throw new CustomException(ErrorCode.CART_ITEM_NOT_VALID);
            }

            int itemPrice = cart.getItemPrice() * cart.getItemCount();

            // orderId는 order 테이블의 시퀀스를 사용하기 때문에 나중에 추가
            OrderItem orderItem = OrderItem.builder()
                    .orderId(null)
                    .itemId(cart.getItemId())
                    .price(itemPrice)
                    .itemCount(cart.getItemCount())
                    .itemOption(cart.getItemOption())
                    .build();

            orderItemList.add(orderItem);

            priceBeforeDiscount += itemPrice;
            totalItemCount += cart.getItemCount();
        }

        // 포인트 사용은 총 금액의 10% 이하로만 사용 가능
        if (priceBeforeDiscount < pointUsage * 10) {
            throw new CustomException(TOO_MUCH_POINT_USAGE);
        }

        // orderId 생성
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderIdPrefix = dateFormat.format(currentDate);

        // orderName 생성
        String orderName = totalItemCount > 1 ?
                cartList.get(0).getItemName() + " 외 " + (totalItemCount-1) + "건" :
                cartList.get(0).getItemName();

        Order order = Order.builder()
                .orderId(orderIdPrefix)
                .orderName(orderName)
                .ordererName(deliveryAddress.getName())
                .ordererPhone(deliveryAddress.getPhone())
                .location(deliveryAddress.getLocation())
                .amount(priceBeforeDiscount - pointUsage)
                .priceBeforeDiscount(priceBeforeDiscount)
                .pointUsage(pointUsage)
                .build();

        // order 테이블에 insert
        paymentMapper.insertOrder(order);

        // order 추가 시 생성된 orderId로 orderItem 추가
        paymentMapper.insertOrderItemList(orderItemList, order.getOrderId());

        /*
         * orderId: 2024090222034(YYYYMMDDHHMISS)000001(SEQ)
         * orderName: (상품명) 외 (나머지상품개수)건
         * amount: 결제금액
         */
        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(order.getOrderId());
        response.setOrderName(orderName);
        response.setAmount(order.getAmount());

        return response;
    }

    /**
     * 결제 승인
     *
     * @author 조영욱
     * @apiNote 사용자 결제 이후 결제 승인을 요청한다 (실 결제는 결제 승인까지 완료 후 이루어진다)
     */
    @Override
    @Transactional
    public boolean confirmPayment(ConfirmPaymentRequestDTO dto) throws Exception {

        // todo: 검증 - orderId, amount 비교

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("orderId", dto.getOrderId());
        obj.put("amount", dto.getAmount());
        obj.put("paymentKey", dto.getPaymentKey());

        log.info(secretKey);
        log.info(confirmUrl);
        String authorizations = "Basic " + secretKey;

        URL url = new URL(confirmUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes(StandardCharsets.UTF_8));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // todo: 결제 성공 및 실패 비즈니스 로직 구현
        JsonNode jsonObject;
        try (Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8)) {
            jsonObject = objectMapper.readTree(reader);
        }

        log.info(jsonObject.toString());
        log.info(jsonObject.get(dto.getPaymentKey()));


        if (isSuccess) { // 결제 성공 시


            // todo: payment 테이블 insert
            // paymentKey
            // orderId
            // orderName
            // status
            // approvedAt
            // method

        } else { // 결제 실패 시


        }

        return isSuccess;
    }

}
