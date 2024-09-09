package com.oasis.hworld.payment.controller;

import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.payment.dto.ConfirmPaymentRequestDTO;
import com.oasis.hworld.payment.dto.OrderRequestDTO;
import com.oasis.hworld.payment.dto.OrderResponseDTO;
import com.oasis.hworld.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 결제 컨트롤러
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
@RestController
@RequestMapping(value="/payments", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    /**
     * 주문 생성
     *
     * @author 조영욱
     * @apiNote 주문서 생성 요청 시 주문을 생성한다.
     */
    @PostMapping("/order")
    public ResponseEntity<OrderResponseDTO> addOrder(@RequestBody OrderRequestDTO dto) {

        // todo: memberId 로직 추가
        return ResponseEntity.ok(service.addOrder(dto, 1));
    }

    /**
     * 결제 승인
     *
     * @author 조영욱
     * @apiNote 사용자 결제 이후 결제 승인을 요청한다 (실 결제는 결제 승인까지 완료 후 이루어진다)
     */
    @PostMapping("/confirm")
    public ResponseEntity<CommonResponseDTO> confirmPayment(@RequestBody ConfirmPaymentRequestDTO dto) throws Exception {
        // todo: memberId 로직 추가
        return service.confirmPayment(dto, 1) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "상품이 결제되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "결제를 실패했습니다."));
    }
}