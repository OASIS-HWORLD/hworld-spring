package com.oasis.hworld.notice.controller;

import com.oasis.hworld.notice.dto.NoticeDetailDTO;
import com.oasis.hworld.notice.dto.NoticeSummaryDTO;
import com.oasis.hworld.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 공지사항 컨트롤러
 * @author 조영욱
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	조영욱        최초 생성
 * </pre>
 */
@RestController
@RequestMapping(value="/notices", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService service;

    /**
     * 공지사항 목록 조회
     *
     * @author 조영욱
     * @apiNote 공지사항 목록을 조회한다. Query Parameter를 통해 페이지네이션한다.
     */
    @GetMapping("")
    public ResponseEntity<List<NoticeSummaryDTO>> getNoticeList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "amount", defaultValue = "10") int amount,
            @RequestParam(value = "category", defaultValue = "0") int category) {
        return ResponseEntity.ok(service.getNoticeList(page, amount, category));
    }

    /**
     * 공지사항 상세 조회
     *
     * @author 조영욱
     * @apiNote 공지사항 하나를 상세 조회한다.
     */
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeDetailDTO> getNoticeDetails(@PathVariable("noticeId") int noticeId) {
        return ResponseEntity.ok(service.getNoticeDetails(noticeId));
    }

}
