package com.oasis.hworld.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 회원 주문 내역 상세 정보 응답 DTO
 * @author 김지현
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OrdersDetailResponseDTO {

    // 주문 번호
    private String orderId;
    // 승인 날짜
    private String approvedAt;
    // 주문자 이름
    private String ordererName;
    // 주문자 전화번호
    private String ordererPhone;
    // 주문 주소
    private String location;
    // 총 주문 상품 개수
    private int totalItemCount;
    // 주문 상품 목록
    private List<OrdersItemDTO> itemList;
    // 할인 전 금액
    private int priceBeforeDiscount;
    // 포인트 사용
    private int pointUsage;
    // 총 결제 금액
    private int totalAmount;
    // 결제 수단
    private String method;
    // 상세 결제 수단
    private String methodDetail;
    // 할부 개월 수
    private int installmentsMonth;
}
