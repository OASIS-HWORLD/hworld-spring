package com.oasis.hworld.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATE_KEY(409, "중복되는 아이디나 닉네임이 존재합니다."),
    PASSWORD_NOT_MATCHED(400, "비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    NOT_VALID_USER_INFORMATION(400, "아이디나 비밀번호가 올바르지 않습니다."),
    FORBIDDEN_REQUEST(403, "접근할 권한이 없습니다."),
    QUEST_NOT_EXIST(400, "존재하지 않는 퀘스트입니다."),
    ITEM_COUNT_OUT_OF_RANGE(400, "1부터 99까지 수량으로만 변경 가능합니다."),
    CART_NOT_EXIST(400, "존재하지 않는 장바구니입니다."),
    NOTICE_NOT_EXIST(400, "존재하지 않는 공지사항입니다."),
    DELIVERY_ADDRESS_NOT_EXIST(400, "배송지가 존재하지 않습니다."),
    CART_ITEM_NOT_VALID(400, "주문을 생성할 수 없습니다."),
    TOO_MUCH_POINT_USAGE(400, "포인트 사용 가능량을 초과하였습니다."),
    ORDER_NOT_VALID(400, "주문 정보가 올바르지 않습니다."),
    POST_NOT_EXIST(400, "존재하지 않는 게시글입니다.");


    private final int status;
    private final String message;
}
