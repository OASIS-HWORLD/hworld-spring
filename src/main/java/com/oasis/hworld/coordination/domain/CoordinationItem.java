package com.oasis.hworld.coordination.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 코디 장착 상품 VO
 * @author 김지현
 * @since 2024.09.04
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	김지현        최초 생성
 * </pre>
 */

@Data
@Builder
public class CoordinationItem {

    // 상품 카테고리
    private int categoryId;
    // 코디 ID
    private int coordinationId;
    // 상품 옵션 ID
    private int itemOptionId;

}
