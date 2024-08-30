package com.oasis.hworld.member.controller;

import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/members", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/test")
    public ResponseEntity<CommonResponseDTO> testFunction() {
        log.info(service.test());
        log.info("컨트롤러 잘 됨.");
        return ResponseEntity.ok(new CommonResponseDTO(true, "hihi"));
    }
}
