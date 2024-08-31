package com.oasis.hworld.member.service;

import com.oasis.hworld.member.dto.SignUpRequestDTO;
import com.oasis.hworld.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{

    private final MemberMapper memberMapper;

    @Transactional
    @Override
    public int signUp(SignUpRequestDTO signUpRequestDTO) {
        log.info("회원가입 -> " + signUpRequestDTO.toString());

        return memberMapper.insertMember(signUpRequestDTO);
    }

    @Override
    public boolean checkIdAvailability(String loginId) {
        log.info("아이디 중복 확인 -> " + loginId);

        return memberMapper.selectMemberByLoginId(loginId) == 0;
    }
}
