package com.oasis.hworld.member.service;

import com.oasis.hworld.member.dto.*;
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
 * 2024.09.03   김지현        마이페이지 관련 기능 구현
 * 2024.09.11   김지현        회원 정보 조회 기능 구현
 * 2024.09.12   김지현        페이징 처리
 * </pre>
 */
public interface MemberService {

    /**
     * 포인트 사용 내역 조회
     *
     * @author 김지현
     */
    PageResponseDTO<List<PointHistoryResponseDTO>> getPointHistory(int memberId, int page, int size);

    /**
     * 회원 게시글 목록 조회
     *
     * @author 김지현
     */
    PageResponseDTO<List<PostListResponseDTO>> getMemberPost(int memberId, String orderBy, int page, int size);

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

    /**
     * 회원 주문 내역 전체 조회
     *
     * @author 김지현
     */
    PageResponseDTO<List<OrdersListResponseDTO>> getMemberOrders(int memberId, int page, int size);

    /**
     * 회원 주문 내역 상세 조회
     *
     * @author 김지현
     */
    OrdersDetailResponseDTO getMemberOrdersDetail(String orderId);

    /**
     * 회원 정보 조회
     *
     * @author 김지현
     */
    MemberInfoResponseDTO getMemberInfo(int memberId);
}
