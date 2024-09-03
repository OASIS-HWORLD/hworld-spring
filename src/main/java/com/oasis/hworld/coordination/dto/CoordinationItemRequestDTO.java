package com.oasis.hworld.coordination.dto;

import lombok.*;

/**
 * 코디 저장 요청 DTO
 * @author 김지현
 * @since 2024.09.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CoordinationItemRequestDTO {

    // 상품 카테고리
    private int categoryId;
    // 코디 ID
    private int coordinationId;
    // 상품 옵션 ID
    private int itemOptionId;

}
