package com.oasis.hworld.member.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.member.domain.Member;
import com.oasis.hworld.member.dto.LoginRequestDTO;
import com.oasis.hworld.member.dto.LoginResponseDTO;
import com.oasis.hworld.member.dto.SignUpRequestDTO;
import com.oasis.hworld.member.mapper.MemberMapper;
import com.oasis.hworld.security.dto.JwtTokenDTO;
import com.oasis.hworld.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

import static com.oasis.hworld.common.exception.ErrorCode.*;

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
 * 2024.09.01   김지현        로그인 메서드 추가
 * </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{

    private final MemberMapper memberMapper;

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

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
        if (memberMapper.selectMemberCountByLoginId(signUpRequestDTO.getLoginId()) != 0
            || memberMapper.selectMemberCountByNickname(signUpRequestDTO.getNickname()) != 0)
            throw new CustomException(DUPLICATE_KEY);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signUpRequestDTO.getPassword());
        signUpRequestDTO.setPassword(encodedPassword);

        return memberMapper.insertMember(signUpRequestDTO);
    }

    /**
     * 로그인
     *
     * @author 김지현
     */
    @Transactional
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        log.info("로그인 -> " + loginRequestDTO.toString());

        // 로그인 ID를 사용한 회원 조회
        Member member = memberMapper.selectMemberByLoginId(loginRequestDTO.getLoginId());
        if (member == null || !passwordEncoder.matches(loginRequestDTO.getPassword(), member.getPassword())) {
            throw new CustomException(NOT_VALID_USER_INFORMATION);
        }

        // 토큰 생성
        JwtTokenDTO token = jwtTokenProvider.generateToken(member);
        log.info("토큰 생성 -> " + token.toString());

        return LoginResponseDTO.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    /**
     * 로그인 ID 중복 조회
     *
     * @author 김지현
     */
    @Override
    public boolean checkIdAvailability(String loginId) {
        log.info("아이디 중복 확인 -> " + loginId);

        return memberMapper.selectMemberCountByLoginId(loginId) == 0;
    }

    /**
     * 닉네임 중복 조회
     *
     * @author 김지현
     */
    @Override
    public boolean checkNicknameAvailability(String nickname) {
        log.info("닉네임 중복 확인 -> " + nickname);

        return memberMapper.selectMemberCountByNickname(nickname) == 0;
    }

    @Override
    public JwtTokenDTO reissueToken(String loginId, String refreshToken) {
        Member member = memberMapper.selectMemberByLoginId(loginId);
        log.info("member 조회 : " + member.toString());
        if (member == null) {
            throw new CustomException(NOT_VALID_USER_INFORMATION);
        }

        return jwtTokenProvider.generateToken(member);
    }


}