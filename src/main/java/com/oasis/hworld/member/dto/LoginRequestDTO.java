package com.oasis.hworld.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 로그인 요청 DTO
 * @author 김지현
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	김지현        최초 생성
 * </pre>
 */
@Getter
@Setter
@ToString
public class LoginRequestDTO {

    // 로그인 ID
    private String loginId;
    // 비밀번호
    private String password;

}
