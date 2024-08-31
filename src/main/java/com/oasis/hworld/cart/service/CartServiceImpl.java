package com.oasis.hworld.cart.service;

import com.oasis.hworld.cart.domain.Cart;
import com.oasis.hworld.cart.dto.CartDetailDTO;
import com.oasis.hworld.cart.dto.CartItemRequestDTO;
import com.oasis.hworld.cart.dto.CartListResponseDTO;
import com.oasis.hworld.cart.dto.ModifyCartItemCountRequestDTO;
import com.oasis.hworld.cart.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 장바구니 서비스 구현체
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
@Service
@Log4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartMapper mapper;

    /**
     * 회원 장바구니 조회
     *
     * @author 조영욱
     */
    @Override
    public CartListResponseDTO getCartList(int memberId) {
        List<CartDetailDTO> CartDetailDTOList = mapper.selectCartByMemberId(memberId);

        CartDetailDTOList.forEach(cart -> {
            int subTotalPrice = cart.getItemPrice()*cart.getItemCount();
            cart.setSubtotalPrice(subTotalPrice);
        });

        return CartListResponseDTO.from(CartDetailDTOList);
    }

    /**
     * 장바구니에 상품 추가
     *
     * @author 조영욱
     */
    @Override
    public boolean addCart(CartItemRequestDTO dto, int memberId) {
        int itemId = dto.getItemId();
        // 장바구니에 이미 존재
        if (mapper.selectCartByMemberIdAndItemId(memberId, itemId) != null) {
            return false;
        }

        return mapper.insertCart(memberId, itemId) == 1;
    }

    /**
     * 장바구니에서 상품 삭제
     *
     * @author 조영욱
     */
    @Override
    public boolean removeItemFromCart(int cartId, int memberId) {
        // 장바구니의 소유자 검증
        if (!validateCartOwner(cartId, memberId)) {
            return false;
        }

         return mapper.deleteCartByCartId(cartId) == 1;
    }

    /**
     * 장바구니의 상품 개수 변경
     *
     * @author 조영욱
     */
    @Override
    public boolean modifyCartItemCount(ModifyCartItemCountRequestDTO dto, int memberId) {
        int cartId = dto.getCartId();
        int itemCount = dto.getItemCount();

        // 장바구니의 소유자 검증
        if (!validateCartOwner(cartId, memberId)) {
            return false;
        }

        // todo: 변경 가능한 상품 개수인지 검증

        return mapper.updateItemCountByCartId(cartId, itemCount) == 1;
    }

    /**
     * 장바구니 소유자 검증
     *
     * @author 조영욱
     */
    private boolean validateCartOwner(int cartId, int memberId) {
        Cart cart = mapper.selectCartByCartId(cartId);

        return cart != null && cart.getMemberId() == memberId;
    }
}
