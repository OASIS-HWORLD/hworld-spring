package com.oasis.hworld.member.controller;

import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.member.dto.SignUpRequestDTO;
import com.oasis.hworld.member.service.AuthService;
import com.oasis.hworld.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 회원 컨트롤러
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
@RestController
@RequestMapping(value="/members", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    /**
     * 회원가입
     *
     * @author 김지현
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Integer> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok(authService.signUp(signUpRequestDTO));
    }

    /**
     * 아이디 중복 검사
     *
     * @author 김지현
     */
    @GetMapping("/check-id")
    public ResponseEntity<CommonResponseDTO> checkIdAvailability(@RequestParam("loginId") String loginId) {
        return authService.checkIdAvailability(loginId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "사용 가능한 아이디입니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "이미 사용 중인 아이디입니다."));
    }

    /**
     * 닉네임 중복 검사
     *
     * @author 김지현
     */
    @GetMapping("/check-nickname")
    public ResponseEntity<CommonResponseDTO> checkNicknameAvailability(@RequestParam("nickname") String nickname) {
        return authService.checkNicknameAvailability(nickname) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "사용 가능한 닉네임입니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "이미 사용 중인 닉네임입니다."));
    }

}
