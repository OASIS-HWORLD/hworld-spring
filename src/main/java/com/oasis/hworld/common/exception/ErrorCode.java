package com.oasis.hworld.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATE_KEY(409, "중복되는 아이디나 닉네임이 존재합니다."),
    PASSWORD_NOT_MATCHED(400, "비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    QUEST_NOT_EXIST(400, "존재하지 않는 퀘스트입니다."),
    ITEM_COUNT_OUT_OF_RANGE(400, "1부터 99까지 수량으로만 변경 가능합니다."),
    CART_NOT_EXIST(400, "존재하지 않는 장바구니입니다."),
    NOTICE_NOT_EXIST(400, "존재하지 않는 공지사항입니다.");

    private final int status;
    private final String message;
}
