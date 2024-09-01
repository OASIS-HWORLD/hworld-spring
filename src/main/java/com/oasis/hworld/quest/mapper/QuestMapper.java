package com.oasis.hworld.quest.mapper;

import com.oasis.hworld.quest.domain.MemberQuest;
import com.oasis.hworld.quest.domain.Quest;
import com.oasis.hworld.quest.dto.QuestDetailDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 퀘스트 Mybatis 인터페이스
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
public interface QuestMapper {

    // 회원 ID로 퀘스트 목록 조회
    List<QuestDetailDTO> selectQuestByMemberId(int memberId);
    // 퀘스트 ID로 퀘스트 조회
    Quest selectQuestByQuestId(int questId);
    // 퀘스트 ID와 회원 ID로 회원 퀘스트 조회
    MemberQuest selectMemberQuestByQuestIdAndMemberId(@Param("questId") int questId, @Param("memberId") int memberId);
    // 회원 ID와 상품 ID로 장바구니 조회
    int insertMemberQuest(@Param("questId") int questId, @Param("memberId") int memberId);
    // 회원 퀘스트 종료 날짜 현재로 변경
    int updateFinishedAt(@Param("questId") int questId, @Param("memberId") int memberId);
}
