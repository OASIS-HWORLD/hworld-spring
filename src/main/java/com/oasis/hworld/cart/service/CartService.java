package com.oasis.hworld.cart.service;

/**
 * 장바구니 서비스 인터페이스
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
public interface CartService {

    int getCartList(int memberId);
}
