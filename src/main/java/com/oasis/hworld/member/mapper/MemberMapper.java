package com.oasis.hworld.member.mapper;

import com.oasis.hworld.member.domain.Member;
import com.oasis.hworld.member.domain.PointHistory;
import com.oasis.hworld.member.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 회원 Mybatis 인터페이스
 * @author 김지현
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------------
 * 2024.08.31  	김지현        최초 생성
 * 2024.09.01   김지현        로그인 ID로 회원 정보 조회 메서드 추가
 * 2024.09.04   김지현        마이페이지 관련 기능 구현
 * 2024.09.07   조영욱        포인트 변경 기능 추가
 * 2024.09.11   김지현        회원 정보 조회 기능 구현
 * </pre>
 */
public interface MemberMapper {

    // 회원 추가
    int insertMember(SignUpRequestDTO dto);

    // 로그인 ID로 회원 수 조회
    int selectMemberCountByLoginId(String loginId);

    // 닉네임으로 회원 수 조회
    int selectMemberCountByNickname(String nickname);

    // 로그인 ID로 회원 정보 조회
    Member selectMemberByLoginId(String loginId);

    // 회원 ID로 포인트 내역 조회
    List<PointHistoryResponseDTO> selectPointHistoryByMemberId(
            @Param("memberId") int memberId,
            @Param("offset") int offset,
            @Param("size") int size);

    // 회원 ID로 게시글 목록 조회
    List<PostListResponseDTO> selectPostByMemberId(
            @Param("memberId") int memberId,
            @Param("orderBy") String orderBy,
            @Param("offset") int offset,
            @Param("size") int size);

    // 회원 ID로 코디 목록 조회
    List<CoordinationListResponseDTO> selectCoordinationByMemberId(int memberId);

    // 코디 ID로 아이템 목록 조회
    List<CoordinationItemListResponseDTO> selectCoordinationItemByCoordinationId(int coordinationId);

    // 회원 ID로 주문 내역 조회
    List<OrdersListResponseDTO> selectOrdersByMemberId(int memberId);

    // 주문 ID로 주문 상세 조회
    OrdersDetailResponseDTO selectOrdersDetailByOrderId(String orderId);

    // 포인트 내역 추가
    int insertPointHistory(PointHistory pointHistory);

    // 회원 포인트 업데이트
    int updatePoint(@Param("memberId") int memberId, @Param("pointChange") int pointChange);

    // 회원 정보 조회
    MemberInfoResponseDTO selectMemberInfoByMemberId(int memberId);
}
