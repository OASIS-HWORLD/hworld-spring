package com.oasis.hworld.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 상점 아이템 Response DTO
 * @author 정은찬
 * @since 2024.09.05
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.05  	정은찬        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
public class ShopResponseDTO {
    private List<ShopItemDTO> items;
}
