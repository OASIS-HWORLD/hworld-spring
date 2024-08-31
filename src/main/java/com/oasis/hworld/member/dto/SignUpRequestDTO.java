package com.oasis.hworld.member.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
public class SignUpRequestDTO {

    private String loginId;
    private String password;
    private String passwordValidation;
    private String nickname;
    private String gender;
    private Date birthdate;

}
