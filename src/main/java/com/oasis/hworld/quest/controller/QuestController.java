package com.oasis.hworld.quest.controller;

import com.oasis.hworld.common.annotation.MemberId;
import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.quest.dto.QuestDetailDTO;
import com.oasis.hworld.quest.service.QuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 퀘스트 컨트롤러
 * @author 조영욱
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	조영욱        최초 생성
 * 2024.09.07   조영욱        퀘스트 진행 추가
 * </pre>
 */
@RestController
@RequestMapping(value="/quests", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class QuestController {

    private final QuestService service;

    /**
     * 퀘스트 목록 조회
     *
     * @author 조영욱
     * @apiNote 로그인한 회원의 퀘스트 목록을 조회한다.
     */
    @GetMapping("")
    public ResponseEntity<List<QuestDetailDTO>> getQuestList(@MemberId int memberId) {
        return ResponseEntity.ok(service.getQuestList(memberId));
    }

    /**
     * 퀘스트 시작
     *
     * @author 조영욱
     */
    @PostMapping("/start/{questId}")
    public ResponseEntity<CommonResponseDTO> startQuest(@PathVariable("questId") int questId, @MemberId int memberId) {
        return service.startQuest(questId, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "퀘스트가 시작되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "퀘스트를 시작할 수 없습니다."));
    }

    /**
     * 퀘스트 종료
     *
     * @author 조영욱
     */
    @PutMapping("/finish/{questId}")
    public ResponseEntity<CommonResponseDTO> finishQuest(@PathVariable("questId") int questId, @MemberId int memberId) {
        return service.finishQuest(questId, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "퀘스트가 종료되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "퀘스트를 종료할 수 없습니다."));
    }

    /**
     * 퀘스트 진행
     *
     * @author 조영욱
     */
    @PutMapping("/progress/{questId}")
    public ResponseEntity<CommonResponseDTO> progressQuest(@PathVariable("questId") int questId, @MemberId int memberId) {
        return service.progressQuest(questId, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "퀘스트가 진행되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "퀘스트가 진행되지 않았습니다."));
    }
}
