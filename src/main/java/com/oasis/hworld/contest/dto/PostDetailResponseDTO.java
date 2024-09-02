package com.oasis.hworld.contest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 콘테스트 게시글 상세 DTO
 * @author 정은찬
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	정은찬        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
public class PostDetailResponseDTO {
    // 게시글 ID
    private int postId;
    // 게시글 제목
    private String title;
    // 게시글 작성일
    private String createdAt;
    // 코디 이미지
    private String imageUrl;
    // 작성자 닉네임
    private String nickname;
    // 추천수
    private int likeCount;
    // 아이템 리스트
    List<ItemDTO> itemList;
    // 댓글 리스트
    List<ReplyDTO> replyList;
}
