package com.oasis.hworld.payment.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 카드사 enum 클래스
 * @author 조영욱
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	조영욱        최초 생성
 *
 * </pre>
 */
@Getter
@RequiredArgsConstructor
public enum CardIssuer {
    KEB_BC("3K", "기업 BC"),
    GWANGJU_BANK("46", "광주은행"),
    LOTTE_CARD("71", "롯데카드"),
    KDB_INDUSTRIAL_BANK("30", "KDB산업은행"),
    BC_CARD("31", "BC카드"),
    SAMSUNG_CARD("51", "삼성카드"),
    SAEMAUL_GEUMGO("38", "새마을금고"),
    SHINHAN_CARD("41", "신한카드"),
    SHIN_HYUP("62", "신협"),
    CITI_CARD("36", "씨티카드"),
    WOORI_BC_CARD("33", "우리BC카드"),
    WOORI_CARD("W1", "우리카드"),
    POST_BANK_INSURANCE("37", "우체국예금보험"),
    SAVINGS_BANK_CENTRAL_ASSOCIATION("39", "저축은행중앙회"),
    JEONBUK_BANK("35", "전북은행"),
    JEJU_BANK("42", "제주은행"),
    KAKAO_BANK("15", "카카오뱅크"),
    K_BANK("3A", "케이뱅크"),
    TOSS_BANK("24", "토스뱅크"),
    HANA_CARD("21", "하나카드"),
    HYUNDAI_CARD("61", "현대카드"),
    KB_KOOKMIN_CARD("11", "KB국민카드"),
    NH_NONGHYUP_CARD("91", "NH농협카드"),
    SUHYUP_BANK("34", "Sh수협은행");

    private final String code;
    private final String name;

    public static String codeToName(String code) {
        for (CardIssuer issuer : values()) {
            if (issuer.getCode().equals(code)) {
                return issuer.getName();
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
