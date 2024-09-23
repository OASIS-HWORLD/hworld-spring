package com.oasis.hworld.notice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 공지사항 목록 조회 DTO
 * @author 조영욱
 * @since 2024.09.014
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.14  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
public class NoticeListResponseDTO {
    List<NoticeSummaryDTO> noticeList;
    int totalCount;
}
