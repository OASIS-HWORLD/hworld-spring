package com.oasis.hworld.cart.mapper;

import com.oasis.hworld.cart.dto.CartDetailDTO;

import java.util.List;

/**
 * 장바구니 Mybatis 인터페이스
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
public interface CartMapper {

    // 회원 ID로 장바구니 조회
    List<CartDetailDTO> selectCartByMemberId(int memberId);
}
