package com.oasis.hworld.member.service;

import com.oasis.hworld.member.dto.*;
import com.oasis.hworld.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 회원 서비스 구현체
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
@Service
@Log4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    /**
     * 포인트 사용 내역 조회
     *
     * @author 김지현
     */
    @Override
    public List<PointHistoryResponseDTO> getPointHistory(int memberId) {
        return memberMapper.selectPointHistoryByMemberId(memberId);
    }

    /**
     * 회원 게시글 목록 조회
     *
     * @author 김지현
     */
    @Override
    public List<PostListResponseDTO> getMemberPost(int memberId, String orderBy) {
        return memberMapper.selectPostByMemberId(memberId, orderBy);
    }

    /**
     * 회원 코디 목록 조회
     *
     * @author 김지현
     */
    @Override
    public List<CoordinationListResponseDTO> getMemberCoordination(int memberId) {
        return memberMapper.selectCoordinationByMemberId(memberId);
    }

    /**
     * 코디에 사용된 아이템 조회
     *
     * @author 김지현
     */
    public List<CoordinationItemListResponseDTO> getCoordinationItem(int coordinationId) {
        return memberMapper.selectCoordinationItemByCoordinationId(coordinationId);
    }

    /**
     * 회원 주문 내역 전체 조회
     *
     * @author 김지현
     */
    public List<OrdersListResponseDTO> getMemberOrders(int memberId) {
        return memberMapper.selectOrdersByMemberId(memberId);
    }

}
