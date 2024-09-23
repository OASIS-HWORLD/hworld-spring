package com.oasis.hworld.notice.dto;

import com.oasis.hworld.notice.domain.Notice;
import com.oasis.hworld.notice.domain.NoticeCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 공지사항 Summary DTO
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
@Builder
public class NoticeSummaryDTO {
    // 공지사항 ID
    private int noticeId;
    // 공지사항 제목
    private String title;
    // 공지사항 카테고리(ID)
    private int category;
    // 공지사항 카테고리 이름
    private String categoryName;
    // 공지사항 생성 날짜
    private String createdAt;

    public static List<NoticeSummaryDTO> from(List<Notice> noticeList) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<NoticeSummaryDTO> noticeSummaryDTOList = new ArrayList<>();
        for (Notice notice : noticeList) {
            noticeSummaryDTOList.add(
                    NoticeSummaryDTO.builder()
                            .noticeId(notice.getNoticeId())
                            .title(notice.getTitle())
                            .category(notice.getCategory())
                            .categoryName(NoticeCategory.getCategoryName(notice.getCategory()))
                            .createdAt(formatter.format(notice.getCreatedAt()))
                            .build());
        }

        return noticeSummaryDTOList;
    }
}
