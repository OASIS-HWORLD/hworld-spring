package com.oasis.hworld.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 장바구니 조회 Response DTO
 * @author 조영욱
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
@Builder
@ToString
public class CartListResponseDTO {

    // 장바구니 리스트
    private List<CartDetailDTO> cartList;
    // 총 가격
    private int totalPrice;

    public static CartListResponseDTO from(List<CartDetailDTO> cartDetailDTOList) {
        return CartListResponseDTO.builder()
                .cartList(cartDetailDTOList)
                .totalPrice(cartDetailDTOList.stream()
                    .map(CartDetailDTO::getSubtotalPrice)
                    .reduce(0, Integer::sum))
                .build();
    }
}
