package com.oasis.hworld.payment.domain;

import lombok.*;

import java.util.Date;

/**
 * 주문 VO
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
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    // 주문 ID
    private String orderId;
    // 회원 ID
    private int memberId;
    // 주문 이름
    private String orderName;
    // 주문자 이름
    private String ordererName;
    // 주문자 전화번호
    private String ordererPhone;
    // 주문 주소
    private String location;
    // 총 결제 금액 (할인 적용)
    private int amount;
    // 할인 전 금액
    private int priceBeforeDiscount;
    // 포인트 사용량
    private int pointUsage;
    // 주문 날짜
    private Date createdAt;
}
