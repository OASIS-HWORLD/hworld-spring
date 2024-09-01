package com.oasis.hworld.security.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Builder
@ToString
public class JwtTokenDTO {

    private String accessToken;
    private String refreshToken;

}
