package com.oasis.hworld.quest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 퀘스트 Detail DTO
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
@Getter
@Setter
public class QuestDetailDTO {
    // 퀘스트 ID
    private int questId;
    // 퀘스트 제목
    private String title;
    // 퀘스트 내용
    private String content;
    // 퀘스트 시작 일자
    private String startDate;
    // 퀘스트 종료 일자
    private String endDate;
    // 퀘스트 상태
    private int status;
    // 시작가능/진행중/완료
    private String progress;
    // 퀘스트 종료 날짜
    private String finishedAt;
}
