package com.oasis.hworld.member.mapper;

import com.oasis.hworld.member.domain.Member;
import com.oasis.hworld.member.dto.SignUpRequestDTO;

/**
 * 회원 Mybatis 인터페이스
 * @author 김지현
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  	김지현        최초 생성
 * </pre>
 */
public interface MemberMapper {

    // 회원 추가
    int insertMember(SignUpRequestDTO dto);

    // 로그인 ID로 회원 수 조회
    int selectMemberCountByLoginId(String loginId);

    // 닉네임으로 회원 수 조회
    int selectMemberCountByNickname(String nickname);

    Member selectMemberByLoginId(String loginId);
}
