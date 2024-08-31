package com.oasis.hworld.cart.service;

import com.oasis.hworld.cart.dto.GetCartListResponseDTO;

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

    /**
     * 회원 장바구니 조회
     *
     * @author 조영욱
     */
    GetCartListResponseDTO getCartList(int memberId);
}
