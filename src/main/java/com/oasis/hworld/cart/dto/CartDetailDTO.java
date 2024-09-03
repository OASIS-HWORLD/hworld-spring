package com.oasis.hworld.cart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 장바구니 Detail DTO
 * @author 조영욱
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
@ToString
public class CartDetailDTO {
    // 장바구니 ID
    private int cartId;
    // 상품 옵션 ID
    private int itemOptionId;
    // 상품 ID
    private int itemId;
    // 상품 개수
    private int itemCount;
    // 상품 이름
    private String itemName;
    // 상품 가격
    private int itemPrice;
    // 상품 옵션
    private String itemOption;
    // 상품 이미지 URL
    private String itemImageUrl;
    // 상품 디테일 이미지 URL
    private String itemDetailImageUrl;
    // 매장 ID
    private String shopId;
    // 매장 이름
    private String shopName;
    // 매장 이미지 url
    private String shopImageUrl;
    // 상품 개수를 반영한 가격
    private int subtotalPrice;
}
