package com.oasis.hworld.quest.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 퀘스트 진행상황 enum 클래스
 * @author 조영욱
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	조영욱        최초 생성
 * 2024.09.07   조영욱        퀘스트 진행 추가
 * </pre>
 */
@Getter
@RequiredArgsConstructor
public enum QuestProgress {
    START_AVAILABLE("시작가능"),
    IN_PROGRESS("진행중"),
    FINISH_AVAILABLE("완료가능"),
    FINISHED("완료");

    private final String progress;
}
