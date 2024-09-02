package com.oasis.hworld.payment.service;

import com.oasis.hworld.payment.dto.ConfirmPaymentRequestDTO;
import com.oasis.hworld.payment.dto.OrderRequestDTO;
import com.oasis.hworld.payment.dto.OrderResponseDTO;

/**
 * 결제 서비스 인터페이스
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
public interface PaymentService {

    boolean confirmPayment(ConfirmPaymentRequestDTO dto) throws Exception;

    OrderResponseDTO addOrder(OrderRequestDTO dto);
}
