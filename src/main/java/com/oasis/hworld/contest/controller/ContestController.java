package com.oasis.hworld.contest.controller;

import com.oasis.hworld.contest.dto.PostListResponseDTO;
import com.oasis.hworld.contest.dto.PostSummaryDTO;
import com.oasis.hworld.contest.service.ContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 진행중인 콘테스트 게시글 목록 조회
     *
     * @author 정은찬
     * @apiNote 진행중인 콘테스트 게시글 목록을 조회한다.
     */
    @GetMapping("/ongoing")
    public ResponseEntity<PostListResponseDTO> getOngoingContestPostList() {
        return ResponseEntity.ok(service.getOngoingContestPostList());
    }

    /**
     * 완료된 콘테스트 게시글 목록 조회
     *
     * @author 정은찬
     * @apiNote 완료된 콘테스트 게시글 목록을 조회한다.
     */
    @GetMapping("/finished")
    public ResponseEntity<PostListResponseDTO> getFinishedContestPostList() {
        return ResponseEntity.ok(service.getFinishedContestPostList());
    }
}
