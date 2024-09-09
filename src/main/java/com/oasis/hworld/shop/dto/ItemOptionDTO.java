package com.oasis.hworld.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 아이템 옵션 DTO
 * @author 정은찬
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.06  	정은찬        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
public class ItemOptionDTO {
    private int itemOptionId;
    private String itemOption;
}
