package com.oasis.hworld.contest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 코디에 착용된 아이템 DTO
 * @author 정은찬
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	정은찬        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
public class CoordinationItemDTO {
    private String itemName;
    private String itemImageUrl;
    private int categoryId;
    private String categoryName;
}
