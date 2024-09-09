package com.oasis.hworld.character.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 캐릭터 장착 상품 조회 Response DTO
 * @author 조영욱
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
@Builder
public class CharacterItemResponseDTO {
    // 카테고리 ID
    private int categoryId;
    // 상품 옵션 ID
    private int itemOptionId;
    // 상품 옵션 이름
    private String itemOption;
    // 상품 ID
    private int itemId;
    // 상품 이름
    private String itemName;
    // 상품 가격
    private int itemPrice;
    // 상품 이미지 url
    private String imageUrl;
}
