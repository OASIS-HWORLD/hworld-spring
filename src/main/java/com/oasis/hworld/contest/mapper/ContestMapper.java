package com.oasis.hworld.contest.mapper;

import com.oasis.hworld.contest.dto.ItemsDTO;
import com.oasis.hworld.contest.dto.PostDetailDTO;
import com.oasis.hworld.contest.dto.PostSummaryDTO;
import com.oasis.hworld.contest.dto.ReplyDTO;
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
 * 2024.09.01   정은찬        콘테스트 게시글 목록 조회 query parameter 적용, 게시글 상세 조회, 게시글 아이템 조회 추가
 * </pre>
 */
public interface ContestMapper {
    // 콘테스트 게시글 목록 조회
    List<PostSummaryDTO> selectContestPostList(@Param("date") String date, @Param("sortBy") String sortBy, @Param("contestStatus") String contestStatus);
    // 게시글 ID를 통해 게시글 상세 조회
    PostDetailDTO selectContestPostDetailByPostId(@Param("postId") int postId);
    // 게시글 ID를 통해 코디 아이템 조회
    ItemsDTO selectItemsByPostId(@Param("postId") int postId);
}
