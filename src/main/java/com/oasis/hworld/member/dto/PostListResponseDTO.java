package com.oasis.hworld.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 회원 게시글 목록 응답 DTO
 * @author 김지현
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostListResponseDTO {

    // 게시글 ID
    private int postId;
    // 코디 이미지 url
    private String imageUrl;
    // 추천수
    private int likeCount;
    // 댓글수
    private int replyCount;
    // 최종 등수
    private int rank;
    // 작성 날짜
    private Date createdAt;

}
