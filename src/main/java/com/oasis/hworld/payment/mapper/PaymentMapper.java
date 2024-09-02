package com.oasis.hworld.payment.mapper;

import com.oasis.hworld.payment.domain.Order;

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

    int insertOrder(Order order);
}
