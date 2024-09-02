package com.oasis.hworld.member.domain;

import lombok.*;

import java.util.Date;

/**
 * 포인트 내역 VO
 * @author 김지현
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	김지현        최초 생성
 * </pre>
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointHistory {

    // 포인트 내역 ID
    private int pointHistoryId;
    // 회원 ID
    private int memberId;
    // 타입
    private int type;
    // 적립/사용 양
    private int amount;
    // 설명
    private String description;
    // 생성일
    private Date createdAt;

}
