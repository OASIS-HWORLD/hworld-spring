package com.oasis.hworld.quest.domain;

import lombok.Data;

import java.util.Date;

/**
 * 퀘스트 VO
 * @author 조영욱
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	조영욱        최초 생성
 * </pre>
 */
@Data
public class Quest {
    // 퀘스트 ID
    private int questId;
    // 퀘스트 제목
    private String title;
    // 퀘스트 내용
    private String content;
    // 완료 시 지급 포인트
    private int point;
    // 퀘스트 시작 날짜
    private Date startDate;
    // 퀘스트 종료 날짜
    private Date endDate;
}
