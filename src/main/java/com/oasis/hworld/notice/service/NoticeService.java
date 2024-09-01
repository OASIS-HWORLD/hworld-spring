package com.oasis.hworld.notice.service;

import com.oasis.hworld.notice.dto.NoticeDetailDTO;
import com.oasis.hworld.notice.dto.NoticeSummaryDTO;
import com.oasis.hworld.quest.dto.QuestDetailDTO;

import java.util.List;

/**
 * 공지사항 서비스 인터페이스
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
public interface NoticeService {

    /**
     * 공지사항 목록 조회
     *
     * @author 조영욱
     */
    List<NoticeSummaryDTO> getNoticeList(int page, int amount);

    /**
     * 공지사항 상세 조회
     *
     * @author 조영욱
     */
    NoticeDetailDTO getNoticeDetails(int noticeId);

}
