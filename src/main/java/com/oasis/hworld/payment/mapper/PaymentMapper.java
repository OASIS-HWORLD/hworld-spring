package com.oasis.hworld.payment.mapper;

import com.oasis.hworld.payment.domain.Order;
import com.oasis.hworld.payment.domain.OrderItem;
import com.oasis.hworld.payment.domain.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 결제 Mybatis 인터페이스
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
public interface PaymentMapper {
    // 주문 추가
    int insertOrder(Order order);
    // 주문 상품 리스트 추가
    int insertOrderItemList(@Param("orderItemList") List<OrderItem> orderItemList, @Param("orderId") String orderId);
    // 주문 ID로 주문 조회
    Order selectOrderByOrderId(String orderId);
    // 결제 추가
    int insertPayment(Payment payment);
}
