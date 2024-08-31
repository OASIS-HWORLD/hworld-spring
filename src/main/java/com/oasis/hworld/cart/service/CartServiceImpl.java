package com.oasis.hworld.cart.service;

import com.oasis.hworld.cart.dto.CartDetailDTO;
import com.oasis.hworld.cart.dto.CartItemRequestDTO;
import com.oasis.hworld.cart.dto.GetCartListResponseDTO;
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
    public GetCartListResponseDTO getCartList(int memberId) {
        List<CartDetailDTO> CartDetailDTOList = mapper.selectCartByMemberId(memberId);

        CartDetailDTOList.forEach(cart -> {
            int subTotalPrice = cart.getItemPrice()*cart.getItemCount();
            cart.setSubtotalPrice(subTotalPrice);
        });

        return GetCartListResponseDTO.from(CartDetailDTOList);
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
}
