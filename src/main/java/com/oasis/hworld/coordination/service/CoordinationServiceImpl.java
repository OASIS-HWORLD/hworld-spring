package com.oasis.hworld.coordination.service;

import com.oasis.hworld.character.domain.CharacterItem;
import com.oasis.hworld.coordination.domain.Coordination;
import com.oasis.hworld.coordination.domain.CoordinationItem;
import com.oasis.hworld.coordination.dto.CoordinationItemRequestDTO;
import com.oasis.hworld.coordination.dto.CoordinationRequestDTO;
import com.oasis.hworld.coordination.mapper.CoordinationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 코디 서비스 구현체
 * @author 김지현
 * @since 2024.09.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	김지현        최초 생성
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class CoordinationServiceImpl implements CoordinationService {

    private final CoordinationMapper coordinationMapper;

    /**
     * 코디 추가
     *
     * @author 김지현
     */
    @Override
    public boolean addCoordination(CoordinationRequestDTO coordinationRequestDTO, int memberId) {
        Coordination coordination = Coordination.builder()
                .memberId(memberId)
                .title(coordinationRequestDTO.getTitle())
                .imageUrl(coordinationRequestDTO.getImageUrl())
                .build();

        // 코디 추가
        if (coordinationMapper.insertCoordination(coordination) != 1)
            return false;

        // 추가된 코디에서 코디 ID 가져오기
        int coordinationId = coordination.getCoordinationId();

        // 추가할 코디 아이템들에 대해서
        List<Integer> itemOptionIdList = coordinationRequestDTO.getItemOptionIdList();
        for (Integer itemOptionId : itemOptionIdList) {
            // 코디 아이템 추가
            if (coordinationMapper.insertCoordinationItem(coordinationId, itemOptionId) != 1)
                return false;
        }

        return true;
    }

    /**
     * 저장된 코디 적용
     *
     * @author 김지현
     */
    @Override
    public boolean applyCoordination(List<CoordinationItemRequestDTO> coordinationItemList, int memberId) {
        boolean result = false;
        for (CoordinationItemRequestDTO coordinationItem : coordinationItemList) {
            int categoryId = coordinationItem.getCategoryId();
            int itemOptionId = coordinationItem.getItemOptionId();

            CharacterItem characterItem = new CharacterItem();
            characterItem.setMemberId(memberId);
            characterItem.setCategoryId(categoryId);
            characterItem.setItemOptionId(itemOptionId);

            if (coordinationMapper.selectCharacterItemByCoordinationItem(categoryId, memberId) > 0) {
                // 이미 아이템이 장착된 카테고리인 경우
                log.info("아이템 장착 O");
                result = coordinationMapper.updateCharacterItemByCoordination(characterItem) == 1;
            } else {
                // 아이템이 장착되어 있지 않은 카테고리인 경우
                log.info("아이템 장착 X");
                result = coordinationMapper.insertCharacterItemByCoordination(characterItem) == 1;
            }

        }

        return result;
    }

    /**
     * 코디 삭제
     *
     * @author 김지현
     */
    @Override
    public boolean deleteCoordination(int coordinationId) {
        return coordinationMapper.deleteCoordination(coordinationId) == 1;
    }

}
