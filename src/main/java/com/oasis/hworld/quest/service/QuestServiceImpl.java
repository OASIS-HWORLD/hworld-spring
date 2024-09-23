package com.oasis.hworld.quest.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.member.domain.PointHistory;
import com.oasis.hworld.member.mapper.MemberMapper;
import com.oasis.hworld.quest.domain.MemberQuest;
import com.oasis.hworld.quest.domain.Quest;
import com.oasis.hworld.quest.domain.QuestProgress;
import com.oasis.hworld.quest.dto.QuestDetailDTO;
import com.oasis.hworld.quest.mapper.QuestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.oasis.hworld.common.exception.ErrorCode.*;

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
 * 2024.09.07   조영욱        퀘스트 진행 추가
 * 2024.09.07   조영욱        퀘스트 완료 시 포인트 지급 추가
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {

    private final QuestMapper questMapper;
    private final MemberMapper memberMapper;

    /**
     * 퀘스트 목록 조회
     *
     * @author 조영욱
     */
    @Override
    public List<QuestDetailDTO> getQuestList(int memberId) {
        List<QuestDetailDTO> questDetailDTOList = questMapper.selectQuestByMemberId(memberId);

        questDetailDTOList.forEach(quest -> {
            if (quest.getStatus() == 0) {
                quest.setProgress(QuestProgress.START_AVAILABLE.getProgress());
            } else if (quest.getFinishedAt() == null && quest.getStatus() == 1) {
                quest.setProgress(QuestProgress.IN_PROGRESS.getProgress());
            }
            else if (quest.getFinishedAt() == null && quest.getStatus() == 2) {
                quest.setProgress(QuestProgress.FINISH_AVAILABLE.getProgress());
            } else {
                quest.setProgress(QuestProgress.FINISHED.getProgress());
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
        if (questMapper.selectMemberQuestByQuestIdAndMemberId(questId, memberId) != null) {
            return false;
        }

        return questMapper.insertMemberQuest(questId, memberId) == 1;
    }

    /**
     * 퀘스트 종료
     *
     * @author 조영욱
     */
    @Override
    public boolean finishQuest(int questId, int memberId) {
        Quest quest = validateQuestExist(questId);

        MemberQuest memberQuest = questMapper.selectMemberQuestByQuestIdAndMemberId(questId, memberId);

        // 퀘스트를 시작하지 않았거나, 이미 완료된 퀘스트일 시 false
        if (memberQuest == null || memberQuest.getFinishedAt() != null) {
            return false;
        }

        // 포인트 지급
        if (quest.getPoint() != 0) {
            memberMapper.updatePoint(memberId, quest.getPoint());
            PointHistory pointHistory = PointHistory.builder()
                    .memberId(memberId)
                    .type(1)
                    .amount(quest.getPoint())
                    .description("퀘스트 | '" + quest.getTitle() + "' 미션 수행 완료")
                    .build();
            memberMapper.insertPointHistory(pointHistory);
        }
        return questMapper.updateFinishedAt(questId, memberId) == 1;
    }

    /**
     * 퀘스트 진행
     *
     * @author 조영욱
     */
    @Override
    public boolean progressQuest(int questId, int memberId) {
        validateQuestExist(questId);

        MemberQuest memberQuest = questMapper.selectMemberQuestByQuestIdAndMemberId(questId, memberId);

        // 퀘스트를 시작하지 않았거나, 이미 완료된 퀘스트일 시 false
        if (memberQuest == null || memberQuest.getFinishedAt() != null) {
            return false;
        }

        return questMapper.updateStatus(questId, memberId, 2) == 1;
    }

    /**
     * 퀘스트 존재 유효성 검증
     *
     * @author 조영욱
     */
    private Quest validateQuestExist(int questId) {
        Quest quest = questMapper.selectQuestByQuestId(questId);
        if (quest == null) {
            throw new CustomException(QUEST_NOT_EXIST);
        }
        return quest;
    }
}
