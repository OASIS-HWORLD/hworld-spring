package com.oasis.hworld.contest.controller;

import com.oasis.hworld.cart.dto.CartItemRequestDTO;
import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.contest.dto.*;
import com.oasis.hworld.contest.service.ContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
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
 * 2024.09.01   정은찬        파라미터를 통해 콘테스트 게시글 목록 조회 메소드 통합, 게시글 상세 조회 메소드 추가
 * 2024.09.02   정은찬        코디 목록 조회 메소드, 진행중인 콘테스트 게시글 등록 메소드, 댓글 등록/삭제 메소드, 게시글 추천 여부 확인 메소드 추가
 * 2024.09.03   정은찬        콘테스트 게시글 추천하기 메소드 추가
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
    public ResponseEntity<PostDetailResponseDTO> getPostDetail(@PathVariable("postId") int postId) {
        return ResponseEntity.ok(service.getPostDetail(postId));
    }

    /**
     * 코디 목록 조회
     *
     * @author 정은찬
     * @apiNote 코디 목록을 조회한다.
     */
    @GetMapping("/coordination/{memberId}")
    public ResponseEntity<List<CoordinationResponseDTO>> getCoordinationList(@PathVariable("memberId") int memberId) {
        return ResponseEntity.ok(service.getCoordinationList(memberId));
    }

    /**
     * 진행중인 콘테스트 게시글 등록
     *
     * @author 정은찬
     * @apiNote 진행중인 콘테스트 게시글을 등록한다.
     */
    @PostMapping("/posts")
    ResponseEntity<CommonResponseDTO> addContestPost(@RequestBody PostRequestDTO postRequestDTO) {
        // todo : memberId 로직 추가
        return service.addContestPost(7, postRequestDTO) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "콘테스트 게시글이 등록되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "같은 코디 게시글이 존재합니다."));
    }

    /**
     * 콘테스트 게시글 댓글 등록
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글의 댓글을 등록한다.
     */
    @PostMapping("/reply")
    ResponseEntity<CommonResponseDTO> addReply(@RequestBody ReplyRequestDTO replyRequestDTO) {
        // todo : memberId 로직 추가
        return service.addReply(1, replyRequestDTO) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "댓글이 등록되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "댓글이 등록되지 않았습니다."));
    }

    /**
     * 콘테스트 게시글 댓글 삭제
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글의 댓글을 삭제한다.
     */
    @DeleteMapping("/reply/{replyId}")
    ResponseEntity<CommonResponseDTO> removeReply(@PathVariable int replyId) {
        // todo : memberId 로직 추가
        return service.removeReply(1, replyId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "댓글이 삭제되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "댓글이 삭제되지 않았습니다."));
    }

    /**
     * 콘테스트 게시글 추천 여부 확인
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글 추천 여부를 확인한다.
     */
    @GetMapping("/recommend/{postId}")
    ResponseEntity<Boolean> checkRecommend(@PathVariable int postId) {
        // todo : memberId 로직 추가
        return ResponseEntity.ok(service.checkRecommend(10, postId));

    }

    /**
     * 콘테스트 게시글 추천하기
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글을 추천한다.
     */
    @PostMapping("/recommend/{postId}")
    ResponseEntity<CommonResponseDTO> addRecommend(@PathVariable int postId) {
        // todo : memberId 로직 추가
        return service.addRecommend(1, postId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "게시글을 추천했습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "게시글 추천을 실패했습니다."));
    }
}

