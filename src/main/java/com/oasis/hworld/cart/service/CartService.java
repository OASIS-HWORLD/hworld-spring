package com.oasis.hworld.cart.service;

import com.oasis.hworld.cart.dto.CartItemRequestDTO;
import com.oasis.hworld.cart.dto.GetCartListResponseDTO;
import com.oasis.hworld.cart.dto.ModifyCartItemCountRequestDTO;

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

    /**
     * 장바구니에 상품 추가
     *
     * @author 조영욱
     */
    boolean addCart(CartItemRequestDTO dto, int memberId);

    /**
     * 장바구니에서 상품 삭제
     *
     * @author 조영욱
     */
    boolean removeItemFromCart(int cartId, int memberId);

    /**
     * 장바구니의 상품 개수 변경
     *
     * @author 조영욱
     */
    boolean modifyCartItemCount(ModifyCartItemCountRequestDTO dto, int memberId);
}
