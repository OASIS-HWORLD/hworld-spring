package com.oasis.hworld.payment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 주문 상품 VO
 * @author 조영욱
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	조영욱        최초 생성
 * 2024.09.03   조영욱        Item -> ItemOption 변경
 * </pre>
 */
@Getter
@Setter
@Builder
public class OrderItem {
    // 주문 ID
    private String orderId;
    // 상품 옵션 ID
    private int itemOptionId;
    // 주문 당시 상품 가격 (개수 적용)
    private int price;
    // 상품 개수
    private int itemCount;
}
