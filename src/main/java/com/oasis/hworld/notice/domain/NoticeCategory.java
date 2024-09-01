package com.oasis.hworld.notice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 공지사항 카테고리 enum 클래스
 * @author 조영욱
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	조영욱        최초 생성
 *
 * </pre>
 */
@Getter
@RequiredArgsConstructor
public enum NoticeCategory {
    CONTEST(1, "콘테스트"),
    NOTICE(2, "공지"),
    EVENT(3, "이벤트");

    private final int categoryId;
    private final String categoryName;

    public static String getCategoryName(int categoryId) {
        for (NoticeCategory category : NoticeCategory.values()) {
            if (category.getCategoryId() == categoryId) {
                return category.getCategoryName();
            }
        }
        throw new IllegalArgumentException("유효하지 않은 카테고리 ID " + categoryId);
    }
}
