package com.oasis.hworld.member.dto;

import lombok.*;

import java.sql.Date;

/**
 * 회원가입 요청 DTO
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
@Getter
@Setter
@ToString
public class SignUpRequestDTO {

    // 로그인 ID
    private String loginId;
    // 비밀번호
    private String password;
    // 비밀번호 확인
    private String passwordValidation;
    // 닉네임
    private String nickname;
    // 성별
    private String gender;
    // 생년월일
    private Date birthdate;

}
