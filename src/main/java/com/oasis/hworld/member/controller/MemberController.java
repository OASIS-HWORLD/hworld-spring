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

@RestController
@RequestMapping(value="/members", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Integer> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok(authService.signUp(signUpRequestDTO));
    }

    @GetMapping("/check-id")
    public ResponseEntity<CommonResponseDTO> checkIdAvailability(@RequestParam("loginId") String loginId) {
        return authService.checkIdAvailability(loginId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "사용 가능한 아이디입니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "이미 사용 중인 아이디입니다."));
    }

}
