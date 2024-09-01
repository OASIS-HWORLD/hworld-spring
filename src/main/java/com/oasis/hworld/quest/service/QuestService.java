package com.oasis.hworld.quest.service;

import com.oasis.hworld.cart.dto.CartItemRequestDTO;
import com.oasis.hworld.cart.dto.CartListResponseDTO;
import com.oasis.hworld.cart.dto.ModifyCartItemCountRequestDTO;
import com.oasis.hworld.quest.dto.QuestDetailDTO;

import java.util.List;

/**
 * 퀘스트 서비스 인터페이스
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
public interface QuestService {

    /**
     * 퀘스트 목록 조회
     *
     * @author 조영욱
     */
    List<QuestDetailDTO> getQuestList(int memberId);

    /**
     * 퀘스트 시작
     *
     * @author 조영욱
     */
    boolean startQuest(int questId, int memberId);

    /**
     * 퀘스트 종료
     *
     * @author 조영욱
     */
    boolean finishQuest(int questId, int memberId);
}
