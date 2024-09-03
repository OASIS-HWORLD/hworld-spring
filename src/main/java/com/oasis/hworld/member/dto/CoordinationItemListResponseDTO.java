package com.oasis.hworld.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 코디 아이템 내역 응답 DTO
 * @author 김지현
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CoordinationItemListResponseDTO {

    // 상품 카테고리
    private int categoryId;
    // 상품 ID
    private int itemId;
    // 상품 이미지 url
    private String imageUrl;
    // 상품 상세 이미지 url
    private String detailImageUrl;

}
