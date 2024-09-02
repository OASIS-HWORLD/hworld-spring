package com.oasis.hworld.contest.domain;

import lombok.Data;

import java.util.Date;

/**
 * 콘테스트 게시글 VO
 * @author 정은찬
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	정은찬        최초 생성
 * </pre>
 */
@Data
public class Post {
    // 게시글 ID
    private int postId;
    // 회원 ID
    private int memberId;
    // 콘테스트 ID
    private int contestId;
    // 코디 ID
    private int coordinationId;
    // 게시글 제목
    private String title;
    // 작성 날짜
    private Date createdAt;
    // 삭제 날짜
    private Date deletedAt;
    // 추천수
    private int likeCount;
    // 수상 여부 (순위)
    private int rank;
}
