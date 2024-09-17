package com.oasis.hworld.character.controller;

import com.oasis.hworld.character.dto.*;
import com.oasis.hworld.character.service.CharacterService;
import com.oasis.hworld.common.annotation.MemberId;
import com.oasis.hworld.common.dto.CommonResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 캐릭터 컨트롤러
 * @author 조영욱
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	조영욱        최초 생성
 * </pre>
 */
@RestController
@RequestMapping(value="/characters", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService service;

    /**
     * 캐릭터 상태 조회
     *
     * @author 조영욱
     * @apiNote 회원 ID로 캐릭터 상태를 조회한다.
     */
    @GetMapping("/state")
    public ResponseEntity<CharacterStateResponseDTO> getCharacterState(@MemberId int memberId) {
        return ResponseEntity.ok(service.getCharacterState(memberId));
    }

    /**
     * 캐릭터 장착 상품 조회
     *
     * @author 조영욱
     * @apiNote 회원 ID로 캐릭터가 장착 중인 상품을 조회한다.
     */
    @GetMapping("/item")
    public ResponseEntity<List<CharacterItemResponseDTO>> getCharacterItem(@MemberId int memberId) {
        return ResponseEntity.ok(service.getCharacterItemList(memberId));
    }

    /**
     * 캐릭터 상태 변경
     *
     * @author 조영욱
     */
    @PutMapping("/state")
    public ResponseEntity<CommonResponseDTO> modifyCharacterState(@RequestBody CharacterStateRequestDTO dto, @MemberId int memberId) {
        return service.modifyCharacterState(dto, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "변경에 성공했습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "변경에 실패했습니다."));
    }

    /**
     * 캐릭터 생성
     *
     * @author 조영욱
     */
    @PostMapping("")
    public ResponseEntity<CommonResponseDTO> addCharacter(@RequestBody AddCharacterRequestDTO dto, @MemberId int memberId) {
        return service.addCharacter(dto, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "캐릭터 생성에 성공했습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "캐릭터 생성에 실패했습니다."));
    }

    /**
     * 상품 장착
     *
     * @author 조영욱
     * @apiNote 캐릭터에 상품을 장착한다.
     */
    @PostMapping("/item")
    public ResponseEntity<CommonResponseDTO> equipItem(@RequestBody EquipItemRequestDTO dto, @MemberId int memberId) {
        return service.equipItem(dto, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "상품 장착에 성공했습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "상품 장착에 실패했습니다."));
    }

    /**
     * 상품 해제
     *
     * @author 조영욱
     * @apiNote 캐릭터가 장착중인 상품을 해제한다.
     */
    @DeleteMapping("/item/{categoryId}")
    public ResponseEntity<CommonResponseDTO> unequipItem(@PathVariable("categoryId") int categoryId, @MemberId int memberId) {
        return service.unequipItem(categoryId, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "장착을 해제했습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "해당 상품을 장착중이지 않습니다."));
    }
}
