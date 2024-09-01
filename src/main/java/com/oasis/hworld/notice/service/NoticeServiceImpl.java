package com.oasis.hworld.notice.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.notice.domain.Notice;
import com.oasis.hworld.notice.dto.NoticeDetailDTO;
import com.oasis.hworld.notice.dto.NoticeSummaryDTO;
import com.oasis.hworld.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.oasis.hworld.common.exception.ErrorCode.*;

/**
 * 공지사항 서비스 구현체
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
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper mapper;

    /**
     * 공지사항 목록 조회
     *
     * @author 조영욱
     */
    public List<NoticeSummaryDTO> getNoticeList(int page, int amount, int category) {
        List<Notice> noticeList = mapper.selectNoticeWithPage(page, amount, category);

        return NoticeSummaryDTO.from(noticeList);
    }

    /**
     * 공지사항 상세 조회
     *
     * @author 조영욱
     */
    public NoticeDetailDTO getNoticeDetails(int noticeId) {
        Notice notice = mapper.selectNoticeByNoticeId(noticeId);

        if (notice == null) {
            throw new CustomException(NOTICE_NOT_EXIST);
        }

        return NoticeDetailDTO.from(notice);
    }
}
