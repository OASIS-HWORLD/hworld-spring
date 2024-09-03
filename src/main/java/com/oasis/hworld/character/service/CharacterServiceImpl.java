package com.oasis.hworld.character.service;

import com.oasis.hworld.character.domain.CharacterItem;
import com.oasis.hworld.character.domain.CharacterState;
import com.oasis.hworld.character.dto.*;
import com.oasis.hworld.character.mapper.CharacterMapper;
import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.oasis.hworld.common.exception.ErrorCode.*;

/**
 * 캐릭터 서비스 구현체
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
@Service
@Log4j
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterMapper mapper;

    /**
     * 캐릭터 상태 조회
     *
     * @author 조영욱
     * @apiNote 회원 ID로 캐릭터 상태를 조회한다.
     */
    @Override
    public CharacterStateResponseDTO getCharacterState(int memberId) {
        CharacterState characterState = mapper.selectCharacterStateByMemberId(memberId);

        // 캐릭터가 존재하지 않으면 예외
        if (characterState == null) {
            throw new CustomException(CHARACTER_NOT_EXIST);
        }

        return CharacterStateResponseDTO.from(characterState);
    }

    /**
     * 캐릭터가 장착 중인 상품 조회
     *
     * @author 조영욱
     * @apiNote 회원 ID로 캐릭터가 장착 중인 상품 목록을 조회한다.
     */
    @Override
    public List<CharacterItemResponseDTO> getCharacterItemList(int memberId) {
        return mapper.selectCharacterItemByMemberId(memberId);
    }

    /**
     * 캐릭터 상태 변경
     *
     * @author 조영욱
     */
    @Override
    public boolean modifyCharacterState(CharacterStateRequestDTO dto, int memberId) {
        return mapper.updateCharacterState(dto, memberId) == 1;
    }

    /**
     * 캐릭터 생성
     *
     * @author 조영욱
     */
    @Override
    public boolean addCharacter(AddCharacterRequestDTO dto, int memberId) {
        CharacterState characterState = mapper.selectCharacterStateByMemberId(memberId);

        // 캐릭터는 한 회원 당 하나만 생성 가능
        if (characterState != null) {
            throw new CustomException(CHARACTER_ALREADY_EXIST);
        }
        return mapper.insertCharacterState(dto, memberId) == 1;
    }

    /**
     * 상품 장착
     *
     * @author 조영욱
     * @apiNote 캐릭터에 상품을 장착한다.
     */
    @Override
    public boolean equipItem(EquipItemRequestDTO dto, int memberId) {
        int itemOptionId = dto.getItemOptionId();

        CharacterState characterState = mapper.selectCharacterStateByMemberId(memberId);

        // 캐릭터가 존재하지 않으면 예외
        if (characterState == null) {
            throw new CustomException(CHARACTER_NOT_EXIST);
        }

        Integer categoryId = mapper.selectCategoryIdByItemOptionId(itemOptionId);

        if (categoryId == null) {
            throw new CustomException(ITEM_NOT_EXIST);
        }

        // 카테고리 ID로 같은 카테고리에 장착중인 캐릭터 상품 조회
        CharacterItem characterItem = mapper.selectCharacterItemByCategoryIdAndMemberId(categoryId, memberId);
        log.info(characterItem);

        if (characterItem == null) {
            // 같은 부위의 상품 착용 중이지 않을 시 착용 상품 insert
            characterItem = new CharacterItem();
            characterItem.setMemberId(memberId);
            characterItem.setCategoryId(categoryId);
            characterItem.setItemOptionId(itemOptionId);
            return mapper.insertCharacterItem(characterItem) == 1;
        } else {
            // 이미 같은 부위의 상품 착용 중일 시, 착용 상품 update
            characterItem.setItemOptionId(itemOptionId);
            return mapper.updateCharacterItem(characterItem) == 1;
        }
    }
}
