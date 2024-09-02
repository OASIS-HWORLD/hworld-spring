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

    /**
     * 주문 생성
     *
     * @author 조영욱
     * @apiNote 주문서 생성 요청 시 주문을 생성한다.
     */
    OrderResponseDTO addOrder(OrderRequestDTO dto, int memberId);

    /**
     * 결제 승인
     *
     * @author 조영욱
     * @apiNote 사용자 결제 이후 결제 승인을 요청한다 (실 결제는 결제 승인까지 완료 후 이루어진다)
     */
    boolean confirmPayment(ConfirmPaymentRequestDTO dto) throws Exception;
}
