package com.oasis.hworld.contest.controller;

import com.oasis.hworld.common.annotation.MemberId;
import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.contest.dto.*;
import com.oasis.hworld.contest.service.ContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * 콘테스트 컨트롤러
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------------------------------------------------------------
 * 2024.08.31  	정은찬        최초 생성
 * 2024.09.01   정은찬        파라미터를 통해 콘테스트 게시글 목록 조회 메소드 통합, 게시글 상세 조회 메소드 추가
 * 2024.09.02   정은찬        코디 목록 조회 메소드, 진행중인 콘테스트 게시글 등록 메소드, 댓글 등록/삭제 메소드 추가
 * 2024.09.03   정은찬        콘테스트 게시글 추천하기 메소드, 게시글 추천 취소하기 메소드 추가, 댓글 삭제 메소드 수정, 게시글 삭제 메소드 추가
 * 2024.09.12   조영욱        베스트 코디 조회 추가
 * 2024.09.15   조영욱        게시글 생성 시 이미지 업로드 추가
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
    public ResponseEntity<PostResponseDTO> getContestPostList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "amount", defaultValue = "8") int amount,
            @RequestParam("status") String contestStatus,
            @RequestParam(value = "sortBy", required = false, defaultValue = "latest") String sortBy,
            @RequestParam(value = "month") String month,
            @MemberId int memberId) {
        return ResponseEntity.ok(service.getContestPostList(page, amount, contestStatus, sortBy, memberId, month));
    }

    /**
     * 콘테스트 게시글 상세 조회
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글을 상세 조회한다.
     */
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailResponseDTO> getPostDetail(@PathVariable("postId") int postId, @MemberId int memberId) {
        return ResponseEntity.ok(service.getPostDetail(postId, memberId));
    }

    /**
     * 코디 목록 조회
     *
     * @author 정은찬
     * @apiNote 코디 목록을 조회한다.
     */
    @GetMapping("/coordination")
    public ResponseEntity<List<CoordinationResponseDTO>> getCoordinationList(@MemberId int memberId) {
        return ResponseEntity.ok(service.getCoordinationList(memberId));
    }

    /**
     * 진행중인 콘테스트 게시글 등록
     *
     * @author 정은찬
     * @apiNote 진행중인 콘테스트 게시글을 등록한다.
     */
    @PostMapping(value = "/posts",consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<CommonResponseDTO> addContestPost(
            @RequestPart("request") @Valid PostRequestDTO postRequestDTO,
            BindingResult bs,
            @RequestPart("file") MultipartFile file,
            @MemberId int memberId) {
        if (bs.hasErrors()) {
            log.info(bs);
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "게시글 업로드를 실패했습니다."));
        }

        return service.addContestPost(memberId, postRequestDTO, file) ?
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
    ResponseEntity<CommonResponseDTO> addReply(@RequestBody ReplyRequestDTO replyRequestDTO, @MemberId int memberId) {
        return service.addReply(memberId, replyRequestDTO) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "댓글이 등록되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "댓글이 등록되지 않았습니다."));
    }

    /**
     * 콘테스트 게시글 댓글 삭제
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글의 댓글을 삭제한다.
     */
    @DeleteMapping("/reply")
    ResponseEntity<CommonResponseDTO> removeReply(@RequestParam("postId") int postId, @RequestParam("replyId") int replyId, @MemberId int memberId) {
        return service.removeReply(memberId, postId, replyId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "댓글이 삭제되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "댓글이 삭제되지 않았습니다."));
    }

    /**
     * 콘테스트 게시글 추천하기
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글을 추천한다.
     */
    @PostMapping("/recommend/{postId}")
    ResponseEntity<CommonResponseDTO> addRecommend(@PathVariable int postId, @MemberId int memberId) {
        return service.addRecommend(memberId, postId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "게시글을 추천했습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "게시글 추천을 실패했습니다."));
    }

    /**
     * 콘테스트 게시글 추천 취소하기
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글 추천을 취소한다.
     */
    @DeleteMapping("/recommend/{postId}")
    ResponseEntity<CommonResponseDTO> removeRecommend(@PathVariable int postId, @MemberId int memberId) {
        return service.removeRecommend(memberId, postId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "게시글 추천을 취소했습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "게시글 추천 취소를 실패했습니다."));
    }

    /**
     * 콘테스트 게시글 삭제하기
     *
     * @author 정은찬
     * @apiNote 콘테스트 게시글을 삭제한다.
     */
    @DeleteMapping("/posts/{postId}")
    ResponseEntity<CommonResponseDTO> removePost(@PathVariable int postId, @MemberId int memberId) {
        return service.removePost(memberId, postId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "게시글을 삭제했습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "게시글 삭제를 실패했습니다."));
    }

    /**
     * 월을 통한 콘테스트 게시글 수상작 목록 조회
     *
     * @author 정은찬
     */
    @GetMapping("/posts/award")
    public ResponseEntity<List<PostAwardDTO>> getContestPostAwardList(
            @RequestParam(value = "month") String month, @MemberId int memberId) {
        return ResponseEntity.ok(service.getPostAwardList(memberId, month));
    }

    /**
     * 베스트 콘테스트 게시글 목록 조회
     *
     * @author 조영욱
     * @apiNote 콘테스트 좋아요 순 베스트 게시글 목록을 조회한다.
     */
    @GetMapping("/posts/best")
    public ResponseEntity<PostResponseDTO> getBestContestPostList(@MemberId int memberId) {
        return ResponseEntity.ok(service.getBestContestPostList(memberId));
    }
}

