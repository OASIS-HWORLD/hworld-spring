package com.oasis.hworld.cart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 장바구니로 주문을 생성하기 위한 DTO
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
@ToString
public class CartOrderDTO {
    // 장바구니 ID
    private int cartId;
    // 회원 ID
    private int memberId;
    // 상품 옵션 ID
    private int itemOptionId;
    // 상품 개수
    private int itemCount;
    // 상품 이름
    private String itemName;
    // 상품 가격
    private int itemPrice;
    // 상품 옵션
    private String itemOption;
}
