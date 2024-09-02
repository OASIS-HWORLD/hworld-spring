package com.oasis.hworld.quest.domain;

import lombok.Data;

import java.util.Date;

/**
 * 회원 퀘스트 VO
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
public class MemberQuest {
    // 회원 ID
    private int memberId;
    // 퀘스트 ID
    private int questId;
    // 상태
    private int status;
    // 완료 날짜
    private Date finishedAt;
}
