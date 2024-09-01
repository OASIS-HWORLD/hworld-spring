package com.oasis.hworld.contest.controller;

import com.oasis.hworld.contest.dto.PostDetailDTO;
import com.oasis.hworld.contest.dto.PostResponseDTO;
import com.oasis.hworld.contest.dto.PostSummaryDTO;
import com.oasis.hworld.contest.service.ContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 콘테스트 컨트롤러
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------
 * 2024.08.31  	정은찬        최초 생성
 * 2024.09.01   정은찬        파라미터를 통해 콘테스트 게시글 목록 조회 통합, 게시글 상세 조회 메소드 추가
 * </pre>
 */
@RestController
@RequestMapping(value="/contest", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class ContestController {
    private final ContestService service;

    /**
     * 콘테스트 게시글 목록 조회
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글 목록을 파라미터를 통해 진행중, 완료, 최신순, 추천순을 조회한다.
     */
    @GetMapping("/posts")
    public ResponseEntity<List<PostSummaryDTO>> getContestPostList(@RequestParam("status") String contestStatus, @RequestParam(value = "sortBy", required = false, defaultValue = "latest") String sortBy) {

        return ResponseEntity.ok(service.getContestPostList(contestStatus, sortBy));
    }

    /**
     * 콘테스트 게시글 상세 조회
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글을 상세 조회한다.
     */
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDTO> getPostDetail(@PathVariable("postId") int postId) {
        return ResponseEntity.ok(service.getPostDetail(postId));
    }
}
