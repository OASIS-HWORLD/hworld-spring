package com.oasis.hworld.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 아이템 상세 정보 DTO
 * @author 김지현
 * @since 2024.09.13
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.13  	김지현        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
public class ItemDetailResponseDTO {
    // 매장 이름
    private String shopName;
    // 매장 이미지
    private String shopImageUrl;
    // 상품 이름
    private String itemName;
    // 가격
    private int price;
    // 상품 이미지
    private String itemImageUrl;
    // 아이템 상세 이미지
    private String itemDetailImageUrl;
    // 옵션 리스트
    private List<ItemOptionDTO> itemOptionList;
}
