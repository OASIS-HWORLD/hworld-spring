package com.oasis.hworld.character.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 상품 장착 Request DTO
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
public class EquipItemRequestDTO {
    // 상품 옵션 ID
    private int itemOptionId;
}
