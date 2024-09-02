package com.oasis.hworld.contest.service;

import com.oasis.hworld.contest.dto.PostDetailResponseDTO;
import com.oasis.hworld.contest.dto.PostSummaryDTO;

import java.util.List;

/**
 * 콘테스트 서비스 인터페이스
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------
 * 2024.08.31  	정은찬        최초 생성
 * 2024.09.01   정은찬        파라미터를 통해 콘테스트 게시글 목록 조회 메소드 통합, 게시글 상세 조회 메소드 추가
 * </pre>
 */
public interface ContestService {
    /**
     * 콘테스트 게시글 목록 조회
     *
     * @author 정은찬
     */
    List<PostSummaryDTO> getContestPostList(String contestStatus, String sortBy);

    /**
     * 게시글 ID를 통해 콘테스트 게시글 상세 조회
     *
     * @author 정은찬
     */
    PostDetailResponseDTO getPostDetail(int postId);
}
