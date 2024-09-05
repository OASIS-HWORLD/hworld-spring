package com.oasis.hworld.coordination.controller;

import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.coordination.domain.CoordinationItem;
import com.oasis.hworld.coordination.dto.CoordinationItemRequestDTO;
import com.oasis.hworld.coordination.dto.CoordinationRequestDTO;
import com.oasis.hworld.coordination.service.CoordinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 코디 컨트롤러
 * @author 김지현
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	김지현        최초 생성
 * </pre>
 */
@RestController
@RequestMapping(value="/coordinations", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class CoordinationController {

    private final CoordinationService coordinationService;

    /**
     * 코디 저장
     *
     * @author 김지현
     */
    @PostMapping("")
    public ResponseEntity<CommonResponseDTO> saveCoordination(@RequestBody CoordinationRequestDTO coordinationRequestDTO) {
        // todo: memberId 로직 추가
        int memberId = 1;
        return coordinationService.addCoordination(coordinationRequestDTO, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "코디가 추가되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "코디 추가에 실패했습니다."));
    }

    /**
     * 저장된 코디 적용
     *
     * @author 김지현
     */
    @PostMapping("/apply-coordination")
    public ResponseEntity<CommonResponseDTO> applyCoordination(@RequestBody List<CoordinationItemRequestDTO> coordinationItemList) {
        // todo: memberId 로직 추가
        int memberId = 1;
        return coordinationService.applyCoordination(coordinationItemList, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "코디가 적용되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "코디 적용에 실패했습니다."));
    }

    /**
     * 코디 삭제
     *
     * @author 김지현
     */
    @DeleteMapping("/{coordinationId}")
    public ResponseEntity<CommonResponseDTO> removeCoordination(@PathVariable("coordinationId") int coordinationId) {
        return coordinationService.deleteCoordination(coordinationId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "코디가 삭제되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "코디 삭제에 실패했습니다."));
    }

}
