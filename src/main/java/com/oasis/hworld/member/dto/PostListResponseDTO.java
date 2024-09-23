package com.oasis.hworld.member.dto;

import lombok.*;

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
 * 2024.09.03   김지현        recommendCount 필드 이름 변경
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PostListResponseDTO {

    // 게시글 ID
    private int postId;
    // 코디 이미지 url
    private String imageUrl;
    // 추천수
    private int recommendCount;
    // 댓글수
    private int replyCount;
    // 최종 등수
    private int rank;
    // 작성 날짜
    private Date createdAt;

}
