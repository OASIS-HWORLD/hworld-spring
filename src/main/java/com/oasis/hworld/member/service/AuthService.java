package com.oasis.hworld.member.service;

import com.oasis.hworld.member.dto.LoginRequestDTO;
import com.oasis.hworld.member.dto.LoginResponseDTO;
import com.oasis.hworld.member.dto.SignUpRequestDTO;

/**
 * 인증/인가 서비스 인터페이스
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
public interface AuthService {

    /**
     * 회원 추가
     *
     * @author 김지현
     */
    int signUp(SignUpRequestDTO signUpRequestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    /**
     * 로그인 ID 중복 조회
     *
     * @author 김지현
     */
    boolean checkIdAvailability(String loginId);

    /**
     * 닉네임 중복 조회
     *
     * @author 김지현
     */
    boolean checkNicknameAvailability(String nickname);

}
