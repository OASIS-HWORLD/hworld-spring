package com.oasis.hworld.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private int memberId;
    private String loginId;
    private String password;
    private String nickname;
    private int point;
    private String gender;
    private Date birthDate;
    private Date createdAt;
    private Date deletedAt;

}
