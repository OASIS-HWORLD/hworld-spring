package com.oasis.hworld.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 주문 Request DTO
 * @author 조영욱
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
public class OrderRequestDTO {
    // 배송지 ID
    private int deliveryAddressId;
    // 포인트 사용량
    private int pointUsage;
    // 주문을 위해 선택한 장바구니 ID 리스트
    List<Integer> cartIdList;
}
