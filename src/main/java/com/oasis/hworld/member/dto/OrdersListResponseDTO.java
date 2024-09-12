package com.oasis.hworld.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 주문 내역 응답 DTO
 * @author 김지현
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	김지현        최초 생성
 * 2024.09.12   김지현        totalCount 필드 추가
 * </pre>
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OrdersListResponseDTO {

    // 주문 ID
    private String orderId;
    // 승인 날짜
    private String approvedAt;
    // 주문 이름
    private String orderName;
    // 총 결제 금액
    private int totalAmount;
    // 전체 데이터 수
    private int totalCount;

}
