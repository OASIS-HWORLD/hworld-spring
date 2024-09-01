package com.oasis.hworld.notice.mapper;

import com.oasis.hworld.notice.domain.Notice;
import com.oasis.hworld.notice.dto.NoticeSummaryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 공지사항 Mybatis 인터페이스
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
public interface NoticeMapper {

    // 공지사항 목록 페이지네이션 조회
    List<Notice> selectNoticeWithPage(@Param("page") int page, @Param("amount") int amount, @Param("category") int category);
    // 공지사항 하나 조회
    Notice selectNoticeByNoticeId(int noticeId);
}
