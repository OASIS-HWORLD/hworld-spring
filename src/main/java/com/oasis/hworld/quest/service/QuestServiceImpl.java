package com.oasis.hworld.quest.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.common.exception.ErrorCode;
import com.oasis.hworld.quest.domain.MemberQuest;
import com.oasis.hworld.quest.domain.Quest;
import com.oasis.hworld.quest.dto.QuestDetailDTO;
import com.oasis.hworld.quest.mapper.QuestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.oasis.hworld.common.exception.ErrorCode.QUEST_NOT_EXIST;

/**
 * 퀘스트 서비스 구현체
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
@Service
@Log4j
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {

    private final QuestMapper mapper;

    /**
     * 퀘스트 목록 조회
     *
     * @author 조영욱
     */
    @Override
    public List<QuestDetailDTO> getQuestList(int memberId) {
        List<QuestDetailDTO> questDetailDTOList = mapper.selectQuestByMemberId(memberId);

        questDetailDTOList.forEach(quest -> {
            if (quest.getStatus() == 0) {
                quest.setProgress("시작가능");
            } else if (quest.getFinishedAt() == null) {
                quest.setProgress("진행중");
            } else {
                quest.setProgress("완료");
            }
        });

        return questDetailDTOList;
    }

    /**
     * 퀘스트 시작
     *
     * @author 조영욱
     */
    @Override
    public boolean startQuest(int questId, int memberId) {
        validateQuestExist(questId);

        // 퀘스트가 진행 중이거나 완료 시 false
        if (mapper.selectMemberQuestByQuestIdAndMemberId(questId, memberId) != null) {
            return false;
        }

        return mapper.insertMemberQuest(questId, memberId) == 1;
    }

    /**
     * 퀘스트 종료
     *
     * @author 조영욱
     */
    @Override
    public boolean finishQuest(int questId, int memberId) {
        validateQuestExist(questId);

        MemberQuest memberQuest = mapper.selectMemberQuestByQuestIdAndMemberId(questId, memberId);

        // 퀘스트를 시작하지 않았거나, 이미 완료된 퀘스트일 시 false
        if (memberQuest == null || memberQuest.getFinishedAt() != null) {
            return false;
        }

        // todo: 포인트 지급 PL/SQL 추가
        return mapper.updateFinishedAt(questId, memberId) == 1;
    }

    /**
     * 퀘스트 존재 유효성 검증
     *
     * @author 조영욱
     */
    private void validateQuestExist(int questId) {
        if (mapper.selectQuestByQuestId(questId) == null) {
            throw new CustomException(QUEST_NOT_EXIST);
        }
    }
}
