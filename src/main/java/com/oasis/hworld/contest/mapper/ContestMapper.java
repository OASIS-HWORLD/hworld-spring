package com.oasis.hworld.contest.mapper;

import com.oasis.hworld.contest.dto.PostSummaryDTO;
import java.util.List;

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
 * 2024.09.01   정은찬        콘테스트 게시글 목록 최신순, 추천순 조회 추가
 * </pre>
 */
public interface ContestMapper {
    // 해당 날짜에 진행중인 콘테스트 게시글 목록 조회 (최신순)
    public List<PostSummaryDTO> selectOngoingContestPostListOrderByLatest(String date);
    // 해당 날짜에 진행중인 콘테스트 게시글 목록 조회 (추천순)
    public List<PostSummaryDTO> selectOngoingContestPostListOrderByRecommend(String date);
    // 완료된 콘테스트 게시글 목록 조회 (최신순)
    public List<PostSummaryDTO> selectFinishedContestPostListOrderByLatest(String date);
    // 완료된 콘테스트 게시글 목록 조회 (추천순)
    public List<PostSummaryDTO> selectFinishedContestPostListOrderByRecommend(String date);
}
