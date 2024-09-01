package com.oasis.hworld.notice.domain;

import lombok.Data;

import java.util.Date;

/**
 * 공지사항 VO
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
@Data
public class Notice {
    // 공지사항 ID
    private int noticeId;
    // 공지사항 제목
    private String title;
    // 공지사항 내용
    private String content;
    // 공지사항 카테고리
    private int category;
    // 공지사항 내부 이미지 url
    private String imageUrl;
    // 공지사항 생성 날짜
    private Date createdAt;
}
