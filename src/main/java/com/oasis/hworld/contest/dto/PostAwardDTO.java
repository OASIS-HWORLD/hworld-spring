package com.oasis.hworld.contest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 콘테스트 수상작 게시글 DTO
 * @author 정은찬
 * @since 2024.09.12
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.12  	정은찬        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
public class PostAwardDTO {
    // 게시글 제목
    private String title;
    // 게시글 아이디
    private int postId;
    // 코디 이미지 url
    private String imageUrl;
    // 추천수
    private int recommendCount;
    // 댓글수
    private int replyCount;
    // 순위
    private int rank;
    // 추천 여부
    private Boolean isRecommended;
    // 게시글 이미지
    private String postImageUrl;
    // 작성자 닉네임
    private String nickname;
}