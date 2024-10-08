package com.oasis.hworld.character.service;

import com.oasis.hworld.character.dto.*;

import java.util.List;

/**
 * 캐릭터 서비스 인터페이스
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
public interface CharacterService {

    /**
     * 캐릭터 상태 조회
     *
     * @author 조영욱
     * @apiNote 회원 ID로 캐릭터 상태를 조회한다.
     */
    CharacterStateResponseDTO getCharacterState(int memberId);

    /**
     * 캐릭터가 장착 중인 상품 조회
     *
     * @author 조영욱
     * @apiNote 회원 ID로 캐릭터가 장착 중인 상품 목록을 조회한다.
     */
    List<CharacterItemResponseDTO> getCharacterItemList(int memberId);

    /**
     * 캐릭터 상태 변경
     *
     * @author 조영욱
     */
    boolean modifyCharacterState(CharacterStateRequestDTO dto, int memberId);

    /**
     * 캐릭터 생성
     *
     * @author 조영욱
     */
    boolean addCharacter(AddCharacterRequestDTO dto, int memberId);

    /**
     * 상품 장착
     *
     * @author 조영욱
     * @apiNote 캐릭터에 상품을 장착한다.
     */
    boolean equipItem(EquipItemRequestDTO dto, int memberId);

    /**
     * 상품 해제
     *
     * @author 조영욱
     * @apiNote 캐릭터가 장착중인 상품을 해제한다.
     */
    boolean unequipItem(int categoryId, int memberId);
}
