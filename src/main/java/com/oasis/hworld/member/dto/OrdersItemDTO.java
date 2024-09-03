package com.oasis.hworld.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 주문 상품 DTO
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
public class OrdersItemDTO {

    // 상품 이미지 url
    private String itemImageUrl;
    // 상품 이름
    private String itemName;
    // 매장 이미지 url
    private String shopImageUrl;
    // 매장 이름
    private String shopName;
    // 상품 옵션
    private String itemOption;
    // 상품 수량
    private int itemCount;
    // 상품 가격
    private int itemPrice;

}
