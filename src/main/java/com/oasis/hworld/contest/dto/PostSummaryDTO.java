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
 * 2024.09.03   정은찬        추천 여부 변수 추가
 * </pre>
 */
@Setter
@Getter
@ToString
public class PostSummaryDTO {
    // 게시글 아이디
    private int postId;

    // 게시글 제목
    private String title;

    // 코디 이미지 url
    private String imageUrl;
    // 추천수
    private int recommendCount;
    // 댓글수
    private int replyCount;
    // 추천 여부
    private Boolean isRecommended;

    // 게시글 이미지 url
    private String postImageUrl;

    // 게시글 작성자
    private String nickname;
}
