package com.oasis.hworld.member.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.member.dto.SignUpRequestDTO;
import com.oasis.hworld.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.oasis.hworld.common.exception.ErrorCode.DUPLICATE_KEY;
import static com.oasis.hworld.common.exception.ErrorCode.PASSWORD_NOT_MATCHED;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{

    private final MemberMapper memberMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public int signUp(SignUpRequestDTO signUpRequestDTO) {
        log.info("회원가입 -> " + signUpRequestDTO.toString());

        if (!signUpRequestDTO.getPassword().equals(signUpRequestDTO.getPasswordValidation()))
            throw new CustomException(PASSWORD_NOT_MATCHED);

        if (memberMapper.selectMemberByLoginId(signUpRequestDTO.getLoginId()) != 0
            || memberMapper.selectMemberByNickname(signUpRequestDTO.getNickname()) != 0)
            throw new CustomException(DUPLICATE_KEY);

        String encodedPassword = passwordEncoder.encode(signUpRequestDTO.getPassword());
        signUpRequestDTO.setPassword(encodedPassword);

        return memberMapper.insertMember(signUpRequestDTO);
    }

    @Override
    public boolean checkIdAvailability(String loginId) {
        log.info("아이디 중복 확인 -> " + loginId);

        return memberMapper.selectMemberByLoginId(loginId) == 0;
    }

    @Override
    public boolean checkNicknameAvailability(String nickname) {
        log.info("닉네임 중복 확인 -> " + nickname);

        return memberMapper.selectMemberByNickname(nickname) == 0;
    }
}