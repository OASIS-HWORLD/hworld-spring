package com.oasis.hworld.contest.mapper;

import com.oasis.hworld.contest.dto.PostSummaryDTO;
import org.apache.ibatis.annotations.Param;

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
 * 2024.09.01   정은찬        쿼리 파라미터를 통해 콘테스트 게시글 조회 통합
 * </pre>
 */
public interface ContestMapper {
    public List<PostSummaryDTO> selectContestPostList(@Param("date") String date,
                                                      @Param("sortBy") String sortBy,
                                                      @Param("contestStatus") String contestStatus);
}
