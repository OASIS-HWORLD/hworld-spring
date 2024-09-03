package com.oasis.hworld.contest.service;

import com.oasis.hworld.contest.dto.*;

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
 * 2024.09.02   정은찬        회원 ID를 통해 코디 목록 조회 메소드, 진행중인 콘테스트 게시글 등록 메소드, 댓글 등록/삭제 메소드, 게시글 추천 여부 확인 메소드 추가
 * 2024.09.03   정은찬        콘테스트 게시글 추천하기 메소드, 게시글 추천 취소하기 메소드 추가, 댓글 등록/삭제 메소드 수정
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

    /**
     * 회원 ID를 통해 코디 목록 조회
     *
     * @author 정은찬
     */
    List<CoordinationResponseDTO> getCoordinationList(int memberId);

    /**
     * 콘테스트 게시글 등록
     *
     * @author 정은찬
     */
    boolean addContestPost(int memberId, PostRequestDTO postRequestDTO);

    /**
     * 콘테스트 게시글 댓글 등록
     *
     * @author 정은찬
     */
    boolean addReply(int memberId, ReplyRequestDTO replyRequestDTO);

    /**
     * 콘테스트 게시글 댓글 삭제
     *
     * @author 정은찬
     */
    boolean removeReply(int memberId, int postId, int replyId);

    /**
     * 콘테스트 게시글 추천 여부 확인
     *
     * @author 정은찬
     */
    boolean checkRecommend(int memberId, int postId);

    /**
     * 콘테스트 게시글 추천하기
     *
     * @author 정은찬
     */
    boolean addRecommend(int memberId, int postId);

    /**
     * 콘테스트 게시글 추천 취소하기
     *
     * @author 정은찬
     */
    boolean removeRecommend(int memberId, int postId);
}
