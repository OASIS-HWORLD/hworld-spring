package com.oasis.hworld.member.controller;

import com.oasis.hworld.member.dto.SignUpRequestDTO;
import com.oasis.hworld.member.service.AuthService;
import com.oasis.hworld.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/member", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Integer> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok(authService.signUp(signUpRequestDTO));
    }
}
