package com.oasis.hworld.coordination.controller;

import com.oasis.hworld.common.annotation.MemberId;
import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.coordination.dto.CoordinationItemRequestDTO;
import com.oasis.hworld.coordination.dto.CoordinationItemResponseDTO;
import com.oasis.hworld.coordination.dto.CoordinationRequestDTO;
import com.oasis.hworld.coordination.service.CoordinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
 * 2024.09.05   김지현        코디에 사용된 아이템 조회 구현
 * 2024.09.06   김지현        장바구니 관련 기능 구현
 * 2023.09.12   조영욱        코디 추가 시 이미지 S3에 업로드
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
    public ResponseEntity<CommonResponseDTO> saveCoordination(
            @RequestBody CoordinationRequestDTO coordinationRequestDTO, @MemberId int memberId) {
        return coordinationService.addCoordination(coordinationRequestDTO, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "코디가 추가되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "코디 추가에 실패했습니다."));
    }

    /**
     * 코디 생성을 위한 이미지 업로드
     *
     * @author 조영욱
     */
    @PostMapping(value = "/image", consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CommonResponseDTO> saveCoordinationImage(@RequestPart("file") MultipartFile file, @MemberId int memberId) {
        String uploadedUrl = coordinationService.addCoordinationImage(file, memberId);

        return uploadedUrl != null ?
                ResponseEntity.ok(new CommonResponseDTO(true, uploadedUrl)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "이미지 추가에 실패했습니다."));

    }

    /**
     * 저장된 코디 적용
     *
     * @author 김지현
     */
    @PostMapping("/apply-coordination")
    public ResponseEntity<CommonResponseDTO> applyCoordination(@RequestBody List<CoordinationItemRequestDTO> coordinationItemList, @MemberId int memberId) {
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

    /**
     * 코디에 사용된 아이템 조회
     *
     * @author 김지현
     */
    @GetMapping("/{coordinationId}")
    public ResponseEntity<List<CoordinationItemResponseDTO>> getCoordinationItem(@PathVariable("coordinationId") int coordinationId, @MemberId int memberId) {
        return ResponseEntity.ok(coordinationService.getCoordinationItem(coordinationId, memberId));
    }

    /**
     * 장바구니에서 상품 삭제
     *
     * @author 김지현
     */
    @DeleteMapping("/cart/{itemOptionId}")
    public ResponseEntity<CommonResponseDTO> removeCart(@PathVariable("itemOptionId") int itemOptionId, @MemberId int memberId) {
        return coordinationService.deleteCart(itemOptionId, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "장바구니에서 상품이 삭제되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "장바구니에 상품이 존재하지 않습니다."));
    }

}
