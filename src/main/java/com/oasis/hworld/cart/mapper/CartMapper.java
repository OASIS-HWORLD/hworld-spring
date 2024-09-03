package com.oasis.hworld.cart.mapper;

import com.oasis.hworld.cart.domain.Cart;
import com.oasis.hworld.cart.dto.CartDetailDTO;
import com.oasis.hworld.cart.dto.CartOrderDTO;
import org.apache.ibatis.annotations.Param;

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
    // 회원 ID와 상품 ID로 장바구니 조회
    Cart selectCartByMemberIdAndItemId(@Param("memberId") int memberId, @Param("itemId") int itemId);
    // 장바구니 ID로 장바구니 조회
    Cart selectCartByCartId(int cartId);
    // 장바구니 추가
    int insertCart(@Param("memberId") int memberId, @Param("itemId") int itemId);
    // 장바구니에서 삭제
    int deleteCartByCartId(int cartId);
    // 장바구니의 상품 개수 변경
    int updateItemCountByCartId(@Param("cartId") int cartId, @Param("itemCount") int itemCount);
    // 장바구니 ID 리스트로 장바구니 조회
    List<CartOrderDTO> selectCartByCartIdList(@Param("cartIdList") List<Integer> cartIdList);
}
