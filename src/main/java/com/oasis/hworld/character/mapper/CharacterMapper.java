package com.oasis.hworld.character.mapper;

import com.oasis.hworld.character.domain.CharacterItem;
import com.oasis.hworld.character.domain.CharacterState;
import com.oasis.hworld.character.dto.AddCharacterRequestDTO;
import com.oasis.hworld.character.dto.CharacterItemResponseDTO;
import com.oasis.hworld.character.dto.CharacterStateRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 캐릭터 Mybatis 인터페이스
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
public interface CharacterMapper {
    // 회원 ID로 캐릭터 상태 조회
    CharacterState selectCharacterStateByMemberId(int memberId);
    // 회원 ID로 캐릭터가 장착 중인 상품 조회
    List<CharacterItemResponseDTO> selectCharacterItemByMemberId(int memberId);
    // 캐릭터 상태 변경
    int updateCharacterState(@Param("dto") CharacterStateRequestDTO dto, @Param("memberId") int memberId);
    // 캐릭터 생성
    int insertCharacterState(@Param("dto") AddCharacterRequestDTO dto, @Param("memberId") int memberId);
    // 카테고리 ID와 회원 ID로 장착중인 캐릭터 상품 조회
    CharacterItem selectCharacterItemByCategoryIdAndMemberId(@Param("categoryId") int categoryId, @Param("memberId") int memberId);
    // 캐릭터 장착 상품 변경
    int updateCharacterItem(CharacterItem characterItem);
    // 상품 옵션 ID로 카테고리 ID 조회
    Integer selectCategoryIdByItemOptionId(int itemOptionId);
    // 캐릭터 장착 상품 추가
    int insertCharacterItem(CharacterItem characterItem);
}
