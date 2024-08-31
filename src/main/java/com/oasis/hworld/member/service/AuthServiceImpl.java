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

/**
 * 인증/인가 서비스 구현체
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
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{

    private final MemberMapper memberMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원 추가
     *
     * @author 김지현
     */
    @Transactional
    @Override
    public int signUp(SignUpRequestDTO signUpRequestDTO) {
        log.info("회원가입 -> " + signUpRequestDTO.toString());

        // 비밀번호 일치 검증
        if (!signUpRequestDTO.getPassword().equals(signUpRequestDTO.getPasswordValidation()))
            throw new CustomException(PASSWORD_NOT_MATCHED);

        // 로그인 ID, 닉네임 중복 검증
        if (memberMapper.selectMemberByLoginId(signUpRequestDTO.getLoginId()) != 0
            || memberMapper.selectMemberByNickname(signUpRequestDTO.getNickname()) != 0)
            throw new CustomException(DUPLICATE_KEY);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signUpRequestDTO.getPassword());
        signUpRequestDTO.setPassword(encodedPassword);

        return memberMapper.insertMember(signUpRequestDTO);
    }

    /**
     * 로그인 ID 중복 조회
     *
     * @author 김지현
     */
    @Override
    public boolean checkIdAvailability(String loginId) {
        log.info("아이디 중복 확인 -> " + loginId);

        return memberMapper.selectMemberByLoginId(loginId) == 0;
    }

    /**
     * 닉네임 중복 조회
     *
     * @author 김지현
     */
    @Override
    public boolean checkNicknameAvailability(String nickname) {
        log.info("닉네임 중복 확인 -> " + nickname);

        return memberMapper.selectMemberByNickname(nickname) == 0;
    }
}