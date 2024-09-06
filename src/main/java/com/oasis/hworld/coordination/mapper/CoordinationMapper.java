package com.oasis.hworld.coordination.mapper;

import com.oasis.hworld.character.domain.CharacterItem;
import com.oasis.hworld.coordination.domain.Coordination;
import com.oasis.hworld.coordination.dto.CoordinationItemResponseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 코디 Mybatis 인터페이스
 * @author 김지현
 * @since 2024.09.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------------
 * 2024.09.04  	김지현        최초 생성
 * 2024.09.05   김지현        코디에 사용된 아이템 조회 구현
 * 2024.09.06   김지현        장바구니 관련 기능 구현
 * </pre>
 */
public interface CoordinationMapper {

    // 코디 추가
    int insertCoordination(Coordination coordination);

    // 코디 아이템 추가
    int insertCoordinationItem(@Param("coordinationId") int coordinationId, @Param("itemOptionId") int itemOptionId);

    // 캐릭터 장착 여부 조회
    int selectCharacterItemByCoordinationItem(@Param("categoryId") int categoryId, @Param("memberId") int memberId);

    // 캐릭터 장착 아이템 변경
    int updateCharacterItemByCoordination(CharacterItem characterItem);

    // 캐릭터 장착 아이템 추가
    int insertCharacterItemByCoordination(CharacterItem characterItem);

    // 코디 삭제
    int deleteCoordination(int coordinationId);

    // 코디 ID로 아이템 목록 조회
    List<CoordinationItemResponseDTO> selectCoordinationItemByCoordinationId(int coordinationId);

    // 아이템 장바구니에 담겼는지 여부 확인
    List<Integer> selectCartByItemOptionIdsAndMemberId(@Param("itemOptionIds") List<Integer> itemOptionIds, @Param("memberId") int memberId);

    // 장바구니에서 아이템 삭제
    int deleteCartByItemOptionIdAndMemberId(@Param("itemOptionId") int itemOptionId, @Param("memberId") int memberId);

}
