package com.oasis.hworld.coordination.service;

import com.oasis.hworld.coordination.dto.CoordinationItemRequestDTO;
import com.oasis.hworld.coordination.dto.CoordinationItemResponseDTO;
import com.oasis.hworld.coordination.dto.CoordinationRequestDTO;

import java.util.List;

/**
 * 코디 서비스 인터페이스
 * @author 김지현
 * @since 2024.09.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	김지현        최초 생성
 * 2024.09.05   김지현        코디에 사용된 아이템 조회 구현
 * 2024.09.06   김지현        장바구니 관련 기능 구현
 * </pre>
 */
public interface CoordinationService {

    /**
     * 코디 저장
     *
     * @author 김지현
     */
    boolean addCoordination(CoordinationRequestDTO coordinationRequestDTO, int memberId);

    /**
     * 저장된 코디 적용
     *
     * @author 김지현
     */
    boolean applyCoordination(List<CoordinationItemRequestDTO> coordinationItemList, int memberId);

    /**
     * 코디 삭제
     *
     * @author 김지현
     */
    boolean deleteCoordination(int coordinationId);

    /**
     * 코디에 사용된 아이템 조회
     *
     * @author 김지현
     */
    List<CoordinationItemResponseDTO> getCoordinationItem(int coordinationId, int memberId);

}
