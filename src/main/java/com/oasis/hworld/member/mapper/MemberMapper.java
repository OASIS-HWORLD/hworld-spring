package com.oasis.hworld.member.mapper;

import com.oasis.hworld.member.domain.Member;
import com.oasis.hworld.member.dto.PostListResponseDTO;
import com.oasis.hworld.member.dto.PointHistoryResponseDTO;
import com.oasis.hworld.member.dto.SignUpRequestDTO;
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
    List<PointHistoryResponseDTO> selectPointHistoryByMemberId(int memberId);

    // 회원 ID로 게시글 목록 조회
    List<PostListResponseDTO> selectPostByMemberId(@Param("memberId") int memberId, @Param("orderBy") String orderBy);
}
