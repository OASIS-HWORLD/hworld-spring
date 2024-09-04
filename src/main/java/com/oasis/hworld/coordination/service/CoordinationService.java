package com.oasis.hworld.coordination.service;

import com.oasis.hworld.coordination.dto.CoordinationItemRequestDTO;
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

}
