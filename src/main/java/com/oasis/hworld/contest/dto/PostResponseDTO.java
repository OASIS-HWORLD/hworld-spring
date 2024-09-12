package com.oasis.hworld.contest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 콘테스트 게시글 목록 Response DTO
 * @author 정은찬
 * @since 2024.09.12
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.12  	정은찬        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
public class PostResponseDTO {
    int totalCount;
    List<PostSummaryDTO> postList;
}
