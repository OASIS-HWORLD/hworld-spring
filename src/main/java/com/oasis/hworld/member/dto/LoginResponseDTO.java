package com.oasis.hworld.member.dto;

import lombok.*;

/**
 * 로그인 응답 DTO
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LoginResponseDTO {

    // 액세스 토큰
    private String accessToken;
    // 리프레시 토큰
    private String refreshToken;
    // 회원 ID
    private int memberId;

}
