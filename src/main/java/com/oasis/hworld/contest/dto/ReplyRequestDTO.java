package com.oasis.hworld.contest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 댓글 등록 RequestDTO
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
@Setter
@Getter
@ToString
public class ReplyRequestDTO {
    private int postId;
    private String content;
}
