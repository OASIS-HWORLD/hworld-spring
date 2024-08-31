package com.oasis.hworld.member.mapper;

import com.oasis.hworld.member.dto.SignUpRequestDTO;

public interface MemberMapper {

    int insertMember(SignUpRequestDTO dto);

    int selectMemberByLoginId(String loginId);

    int selectMemberByNickname(String nickname);
}
