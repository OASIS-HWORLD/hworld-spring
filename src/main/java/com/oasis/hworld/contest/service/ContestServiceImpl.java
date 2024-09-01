package com.oasis.hworld.contest.service;

import com.oasis.hworld.contest.dto.PostSummaryDTO;
import com.oasis.hworld.contest.mapper.ContestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 콘테스트 서비스 구현체
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------
 * 2024.08.31  	정은찬        최초 생성
 * 2024.09.01   정은찬        콘테스트 게시글 목록 조회 최신순, 추천순 메소드 추가
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {

    private final ContestMapper mapper;

    /**
     * 진행중인 콘테스트 게시글 목록 조회 (최신순 정렬)
     *
     * @author 정은찬
     */
    public List<PostSummaryDTO> getOngoingContestPostListOrderByLatest() {
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        List<PostSummaryDTO> postSummaryDTOList = mapper.selectOngoingContestPostListOrderByLatest(formattedDate);

        return postSummaryDTOList;
    }

    /**
     * 진행중인 콘테스트 게시글 목록 조회 (추천순 정렬)
     *
     * @author 정은찬
     */
    public List<PostSummaryDTO> getOngoingContestPostListOrderByRecommend() {
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        List<PostSummaryDTO> postSummaryDTOList = mapper.selectOngoingContestPostListOrderByRecommend(formattedDate);

        return postSummaryDTOList;
    }

    /**
     * 완료된 콘테스트 게시글 목록 조회 (최신순 정렬)
     *
     * @author 정은찬
     */
    public List<PostSummaryDTO> getFinishedContestPostListOrderByLatest() {
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        List<PostSummaryDTO> postSummaryDTOList = mapper.selectFinishedContestPostListOrderByLatest(formattedDate);

        return postSummaryDTOList;
    }

    /**
     * 완료된 콘테스트 게시글 목록 조회 (추천순 정렬)
     *
     * @author 정은찬
     */
    public List<PostSummaryDTO> getFinishedContestPostListOrderByRecommend() {
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        List<PostSummaryDTO> postSummaryDTOList = mapper.selectFinishedContestPostListOrderByRecommend(formattedDate);

        return postSummaryDTOList;
    }
}
