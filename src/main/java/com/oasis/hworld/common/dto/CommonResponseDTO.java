package com.oasis.hworld.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommonResponseDTO {
    // 성공 여부
    private boolean success;
    // 메시지
    private String message;
}