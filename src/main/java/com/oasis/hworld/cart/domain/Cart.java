package com.oasis.hworld.cart.domain;

import lombok.Data;

/**
 * 장바구니 VO
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
@Data
public class Cart {

    private int cartId;
    private int memberId;
    private int itemId;
    private int itemCount;
}
