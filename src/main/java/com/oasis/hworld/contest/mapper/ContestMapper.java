package com.oasis.hworld.contest.mapper;

import com.oasis.hworld.contest.domain.Post;
import com.oasis.hworld.contest.domain.Recommend;
import com.oasis.hworld.contest.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 콘테스트 Mybatis 인터페이스
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------
 * 2024.08.31  	정은찬        최초 생성
 * 2024.09.01   정은찬        콘테스트 게시글 목록 조회 query parameter 적용, 게시글 상세 조회, 게시글 코디 착용 아이템 조회 추가
 * 2024.09.02   정은찬        회원 ID를 통한 코디 목록 조회, 진행중인 콘테스트 게시글 등록, 댓글 등록/삭제, 게시글 추천 조회 추가
 * 2024.09.03   정은찬        콘테스트 게시글 추천 등록 및 추천수 업데이트,  콘테스트 게시글 추천 삭제 및 추천수 업데이트, 댓글수 업데이트(증가, 감소), 게시글 목록 추천 조회 추가
 * </pre>
 */
public interface ContestMapper {
    // 콘테스트 게시글 목록 조회
    List<PostSummaryDTO> selectContestPostList(@Param("date") String date, @Param("sortBy") String sortBy, @Param("contestStatus") String contestStatus);

    // 게시글 ID를 통한 게시글 상세 조회
    PostDetailResponseDTO selectContestPostDetailByPostId(@Param("postId") int postId);

    // 회원 ID를 통한 저장된 코디 조회
    List<CoordinationResponseDTO> selectCoordinationListByMemberId(@Param("memberId") int memberId);

    // 회원 ID와 코디 ID를 통한 콘테스트 게시글 조회
    Post selectContestPostByMemberIdAndCoordinationId(@Param("memberId") int memberId, @Param("coordinationId") int coordinationId);

    // 진행중인 콘테스트 게시글 등록
    int insertContestPost(@Param("memberId") int memberId, @Param("postRequestDTO") PostRequestDTO postRequestDTO);

    // 콘테스트 게시글 댓글 등록
    int insertReply(@Param("memberId") int memberId, @Param("replyRequestDTO") ReplyRequestDTO replyRequestDTO);

    // 콘테스트 게시글 댓글 삭제
    int deleteReply(@Param("memberId") int memberId, @Param("replyId") int replyId);

    // 회원 ID와 게시글 ID를 통한 콘테스트 게시글 추천 조회
    Recommend selectRecommendByMemberIdAndPostId(@Param("memberId") int memberId, @Param("postId") int postId);

    // 콘테스트 게시글 추천 등록 및 추천수 업데이트
    void insertRecommendAndUpdateRecommendCount(Map<String, Object> params);

    // 콘테스트 게시글 추천 삭제 및 추천수 업데이트
    void deleteRecommendAndUpdateRecommendCount(Map<String, Object> params);

    // 콘테스트 게시글 댓글수 업데이트 (증가)
    int updateIncreaseReplyCount(@Param("postId") int postId);

    // 콘테스트 게시글 댓글수 업데이트 (감소)
    int updateDecreaseReplyCount(@Param("postId") int postId);

    // 게시글 목록 추천 조회
    List<Integer> getRecommendedPosts(@Param("memberId") int memberId, @Param("postIds") List<Integer> postIds);

}