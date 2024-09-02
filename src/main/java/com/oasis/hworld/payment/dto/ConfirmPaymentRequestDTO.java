package com.oasis.hworld.payment.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 결제 승인 Request DTO
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
@Getter
@Setter
public class ConfirmPaymentRequestDTO {

    private String orderId;
    private String amount;
    private String paymentKey;
}
