package com.oasis.hworld.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LoginResponseDTO {


    private String accessToken;
    private String refreshToken;
    private int memberId;

}
