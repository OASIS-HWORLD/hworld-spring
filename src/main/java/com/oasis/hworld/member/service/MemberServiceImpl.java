package com.oasis.hworld.member.service;

import com.oasis.hworld.member.dto.PostListResponseDTO;
import com.oasis.hworld.member.dto.PointHistoryResponseDTO;
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

}
