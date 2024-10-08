package com.oasis.hworld.contest.service;

import com.oasis.hworld.contest.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 콘테스트 서비스 인터페이스
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * 2024.08.31  	정은찬        최초 생성
 * 2024.09.01   정은찬        파라미터를 통해 콘테스트 게시글 목록 조회 메소드 통합, 게시글 상세 조회 메소드 추가
 * 2024.09.02   정은찬        회원 ID를 통해 코디 목록 조회 메소드, 진행중인 콘테스트 게시글 등록 메소드, 댓글 등록/삭제 메소드, 게시글 추천 여부 확인 메소드 추가
 * 2024.09.03   정은찬        콘테스트 게시글 추천하기 메소드, 게시글 추천 취소하기 메소드, 게시글 목록 조회 / 상세보기 메소드 추천여부 확인, 게시글 삭제하기 메소드 추가
 * 2024.09.12   조영욱        베스트 코디 조회 추가
 * 2024.09.15   조영욱        게시글 생성 시 이미지 업로드 추가
 * </pre>
 */
public interface ContestService {
    /**
     * 콘테스트 게시글 목록 조회
     *
     * @author 정은찬
     */
    PostResponseDTO getContestPostList(int page, int amount, String contestStatus, String sortBy, int memberId, String month);

    /**
     * 게시글 ID를 통해 콘테스트 게시글 상세 조회
     *
     * @author 정은찬
     */
    PostDetailResponseDTO getPostDetail(int postId, int memberId);

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
    boolean addContestPost(int memberId, PostRequestDTO postRequestDTO, MultipartFile file);

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

    /**
     * 콘테스트 게시글 삭제하기
     *
     * @author 정은찬
     */
    boolean removePost(int memberId, int postId);

    /**
     * 월을 통한 콘테스트 게시글 수상작 목록 조회
     *
     * @author 정은찬
     */
    List<PostAwardDTO> getPostAwardList(int memberId, String month);
    
    /**
     * 베스트 콘테스트 게시글 목록 조회
     *
     * @author 조영욱
     */
    PostResponseDTO getBestContestPostList(int memberId);
}
