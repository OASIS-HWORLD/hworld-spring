package com.oasis.hworld.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequestDTO {

    // 로그인 ID
    private String loginId;
    // 비밀번호
    private String password;

}
