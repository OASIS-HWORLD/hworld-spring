package com.oasis.hworld.coordination.dto;

import lombok.*;

/**
 * 코디 아이템 내역 응답 DTO
 * @author 김지현
 * @since 2024.09.05
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.05  	김지현        최초 생성
 * 2024.09.06   김지현        장바구니 관련 필드 추가
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CoordinationItemResponseDTO {

    // 상품 카테고리
    private int categoryId;
    // 상품 ID
    private int itemId;
    // 상품 옵션 ID
    private int itemOptionId;
    // 상품 이미지 url
    private String imageUrl;
    // 상품 이름
    private String name;
    // 상품 가격
    private int price;
    // 장바구니에 담겨 있는지 여부
    private boolean isInCart;
    // 매장 아이디
    private int shopId;

}
