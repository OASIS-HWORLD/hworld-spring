package com.oasis.hworld.member.service;

import com.oasis.hworld.member.dto.CoordinationItemListResponseDTO;
import com.oasis.hworld.member.dto.CoordinationListResponseDTO;
import com.oasis.hworld.member.dto.PostListResponseDTO;
import com.oasis.hworld.member.dto.PointHistoryResponseDTO;

import java.util.List;

/**
 * 회원 서비스 인터페이스
 * @author 김지현
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	김지현        최초 생성
 * </pre>
 */
public interface MemberService {

    /**
     * 포인트 사용 내역 조회
     *
     * @author 김지현
     */
    List<PointHistoryResponseDTO> getPointHistory(int memberId);

    /**
     * 회원 게시글 목록 조회
     *
     * @author 김지현
     */
    List<PostListResponseDTO> getMemberPost(int memberId, String orderBy);

    /**
     * 회원 코디 목록 조회
     *
     * @author 김지현
     */
    List<CoordinationListResponseDTO> getMemberCoordination(int memberId);

    /**
     * 코디에 사용된 아이템 조회
     *
     * @author 김지현
     */
    List<CoordinationItemListResponseDTO> getCoordinationItem(int coordinationId);
}
