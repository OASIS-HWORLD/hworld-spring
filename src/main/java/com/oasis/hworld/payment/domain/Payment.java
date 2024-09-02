package com.oasis.hworld.payment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 결제 VO
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
@Builder
public class Payment {
    // 토스페이먼츠에서 발급받은 결제 키
    private String paymentKey;
    // 주문 ID
    private String orderId;
    // 결제 상태
    private String status;
    // 승인 날짜
    private Date approvedAt;
    // 총 결제 금액
    private int totalAmount;
    // 결제 수단(카드, 가상계좌, 간편결제, 휴대폰, 계좌이체, 문화상품권, 도서문화상품권, 게임문화상품권)
    private String method;
    // 상세 결제 수단 (ex. method 카드일 시: bc카드, method 간편결제일 시: 카카오페이)
    private String methodDetail;
    // 할부 개월 수
    private int installmentsMonth;
}
