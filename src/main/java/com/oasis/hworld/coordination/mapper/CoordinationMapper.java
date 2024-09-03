package com.oasis.hworld.coordination.mapper;

import com.oasis.hworld.character.domain.CharacterItem;
import com.oasis.hworld.coordination.domain.Coordination;
import com.oasis.hworld.coordination.domain.CoordinationItem;
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

}
