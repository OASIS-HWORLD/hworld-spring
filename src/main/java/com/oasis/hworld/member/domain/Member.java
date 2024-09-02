package com.oasis.hworld.member.domain;

import lombok.*;

import java.util.Date;

/**
 * 회원 VO
 * @author 김지현
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  	김지현        최초 생성
 * 2024.09.01   김지현        권한 목록 필드 추가
 * </pre>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    // 회원 ID
    private int memberId;
    // 로그인 ID
    private String loginId;
    // 비밀번호
    private String password;
    // 닉네임
    private String nickname;
    // 보유 포인트
    private int point;
    // 성별
    private String gender;
    // 생년월일
    private Date birthDate;
    // 가입일
    private Date createdAt;
    // 탈퇴일
    private Date deletedAt;
    // 권한 목록
    private String roles;

}
