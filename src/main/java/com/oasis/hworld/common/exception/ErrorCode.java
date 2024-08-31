package com.oasis.hworld.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATE_KEY(409, "중복되는 아이디나 닉네임이 존재합니다."),
    PASSWORD_NOT_MATCHED(400, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");

    private final int status;
    private final String message;
}
