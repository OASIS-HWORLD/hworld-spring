package com.oasis.hworld.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 상품 카테고리 enum 클래스
 * @author 정은찬
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	정은찬        최초 생성
 *
 * </pre>
 */
@Getter
@RequiredArgsConstructor
public enum ItemCategory {
    HAT(1, "모자"),
    NECKLACE(2, "목걸이"),
    GLASSES(3, "안경"),
    BAG(4, "가방");

    private final int categoryId;
    private final String categoryName;

    public static String getCategoryName(int categoryId) {
        for (ItemCategory category : ItemCategory.values()) {
            if (category.getCategoryId() == categoryId) {
                return category.getCategoryName();
            }
        }
        throw new IllegalArgumentException("유효하지 않은 카테고리 ID " + categoryId);
    }
}

