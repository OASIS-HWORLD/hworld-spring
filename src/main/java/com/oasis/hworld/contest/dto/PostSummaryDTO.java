package com.oasis.hworld.contest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 콘테스트 게시글 요약 DTO
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  	정은찬        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
public class PostSummaryDTO {
    // 게시글 아이디
    private int postId;
    // 코디 이미지 url
    private String imageUrl;
    // 추천수
    private int recommendCount;
    // 댓글수
    private int replyCount;
}
