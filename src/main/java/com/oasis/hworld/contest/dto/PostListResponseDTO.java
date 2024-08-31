package com.oasis.hworld.contest.dto;

import com.oasis.hworld.cart.dto.CartDetailDTO;
import com.oasis.hworld.cart.dto.CartListResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 콘테스트 게시글 조회 Response DTO
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
@Getter
@Setter
@Builder
@ToString
public class PostListResponseDTO {
    // 게시글 리스트
    private List<PostSummaryDTO> postList;

    public static PostListResponseDTO from(List<PostSummaryDTO> postSummaryDTOList) {
        return PostListResponseDTO.builder()
                .postList(postSummaryDTOList)
                .build();
    }
}