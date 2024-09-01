package com.oasis.hworld.contest.controller;

import com.oasis.hworld.contest.dto.PostSummaryDTO;
import com.oasis.hworld.contest.service.ContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 콘테스트 컨트롤러
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  	정은찬        최초 생성
 * </pre>
 */
@RestController
@RequestMapping(value="/contest", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class ContestController {
    private final ContestService service;

    /**
     * 진행중인 콘테스트 게시글 목록 조회 (최신순)
     *
     * @author 정은찬
     * @apiNote 진행중인 콘테스트 게시글 목록을 조회한다.
     */
    @GetMapping("/ongoing/latest")
    public ResponseEntity<List<PostSummaryDTO>> getOngoingContestPostListOrderByLatest() {
        return ResponseEntity.ok(service.getOngoingContestPostListOrderByLatest());
    }

    /**
     * 완료된 콘테스트 게시글 목록 조회
     *
     * @author 정은찬
     * @apiNote 완료된 콘테스트 게시글 목록을 조회한다.
     */
    @GetMapping("/finished/latest")
    public ResponseEntity<List<PostSummaryDTO>> getFinishedContestPostListOrderByLatest() {
        return ResponseEntity.ok(service.getFinishedContestPostListOrderByLatest());
    }
}
