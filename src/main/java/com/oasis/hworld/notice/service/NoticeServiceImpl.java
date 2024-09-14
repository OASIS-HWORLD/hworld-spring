package com.oasis.hworld.notice.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.notice.domain.Notice;
import com.oasis.hworld.notice.dto.NoticeDetailDTO;
import com.oasis.hworld.notice.dto.NoticeListResponseDTO;
import com.oasis.hworld.notice.dto.NoticeSummaryDTO;
import com.oasis.hworld.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
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
 * 2024.09.10   조영욱        S3 도입으로 인한 이미지 URL 변경
 * 2024.09.14    조영욱       공지사항 목록 페이지네이션 조회를 위한 전체 개수 조회 추가
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper mapper;
    @Value("${S3_BUCKET_URL}")
    private String s3BucketUrl;

    /**
     * 공지사항 목록 조회
     *
     * @author 조영욱
     */
    public NoticeListResponseDTO getNoticeList(int page, int amount, int category) {
        List<Notice> noticeList = mapper.selectNoticeWithPage(page, amount, category);

        int totalCount = mapper.selectCountNotice(category);

        NoticeListResponseDTO response = new NoticeListResponseDTO();
        response.setNoticeList(NoticeSummaryDTO.from(noticeList));
        response.setTotalCount(totalCount);

        return response;
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

        // s3 버킷 이미지 url 추가
        if (notice.getImageUrl() != null) {
            notice.setImageUrl(s3BucketUrl + notice.getImageUrl());
        }

        return NoticeDetailDTO.from(notice);
    }
}
