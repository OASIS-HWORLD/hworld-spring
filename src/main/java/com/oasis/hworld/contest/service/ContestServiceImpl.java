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
 * 2024.09.01   정은찬        파라미터를 통해 콘테스트 게시글 목록 조회 메소드 통합
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {

    private final ContestMapper mapper;

    /**
     * 콘테스트 게시글 목록 조회
     *
     * @author 정은찬
     */
    public List<PostSummaryDTO> getContestPostList(String contestStatus, String sortBy) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        return mapper.selectContestPostList(formattedDate, sortBy, contestStatus);
    }
}
