package com.oasis.hworld.contest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 댓글 DTO
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
public class ReplyDTO {
    // 댓글 id
    private int replyId;
    // 작성자 id
    private int memberId;
    // 작성자 닉네임
    private String nickname;
    // 댓글 내용
    private String content;
    // 댓글 작성일
    private String createdAt;
}
