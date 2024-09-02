package com.oasis.hworld.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATE_KEY(409, "중복되는 아이디나 닉네임이 존재합니다."),
    PASSWORD_NOT_MATCHED(400, "비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    NOT_VALID_USER_INFORMATION(400, "아이디나 비밀번호가 올바르지 않습니다."),
    FORBIDDEN_REQUEST(403, "접근할 권한이 없습니다.");

    private final int status;
    private final String message;
}
