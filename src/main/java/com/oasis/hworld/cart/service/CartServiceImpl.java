package com.oasis.hworld.cart.service;

import com.oasis.hworld.cart.domain.Cart;
import com.oasis.hworld.cart.dto.CartDetailDTO;
import com.oasis.hworld.cart.dto.CartItemOptionRequestDTO;
import com.oasis.hworld.cart.dto.CartListResponseDTO;
import com.oasis.hworld.cart.dto.ModifyCartItemCountRequestDTO;
import com.oasis.hworld.cart.mapper.CartMapper;
import com.oasis.hworld.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.oasis.hworld.common.exception.ErrorCode.*;

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
 * 2024.09.03   조영욱        Item -> ItemOption 변경
 * 2024.09.10   조영욱        S3 도입으로 인한 이미지 URL 변경
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartMapper mapper;
    @Value("${S3_BUCKET_URL}")
    private String s3BucketUrl;

    /**
     * 회원 장바구니 조회
     *
     * @author 조영욱
     */
    @Override
    public CartListResponseDTO getCartList(int memberId) {
        List<CartDetailDTO> CartDetailDTOList = mapper.selectCartByMemberId(memberId);

        CartDetailDTOList.forEach(cart -> {
            int subTotalPrice = cart.getItemPrice() * cart.getItemCount();
            cart.setSubtotalPrice(subTotalPrice);

            // s3 버킷 이미지 url 추가
            cart.setItemImageUrl(s3BucketUrl + cart.getItemImageUrl());
            cart.setItemDetailImageUrl(s3BucketUrl + cart.getItemDetailImageUrl());
            cart.setShopImageUrl(s3BucketUrl + cart.getShopImageUrl());
        });

        return CartListResponseDTO.from(CartDetailDTOList);
    }

    /**
     * 장바구니에 상품 추가
     *
     * @author 조영욱
     */
    @Override
    public boolean addCart(CartItemOptionRequestDTO dto, int memberId) {
        int itemOptionId = dto.getItemOptionId();
        // todo: 아이템 존재 검증

        // 장바구니에 이미 존재
        if (mapper.selectCartByMemberIdAndItemOptionId(memberId, itemOptionId) != null) {
            return false;
        }

        return mapper.insertCart(memberId, itemOptionId) == 1;
    }

    /**
     * 장바구니에서 상품 삭제
     *
     * @author 조영욱
     */
    @Override
    public boolean removeItemFromCart(int cartId, int memberId) {
        // 장바구니의 소유자 검증
        validateCartOwner(cartId, memberId);

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
        validateCartOwner(cartId, memberId);

        // 변경 가능한 상품 개수인지 검증
        if (itemCount <= 0 || itemCount > 99) {
            throw new CustomException(ITEM_COUNT_OUT_OF_RANGE);
        }

        return mapper.updateItemCountByCartId(cartId, itemCount) == 1;
    }

    /**
     * 장바구니 소유자 검증
     *
     * @author 조영욱
     */
    private void validateCartOwner(int cartId, int memberId) {
        Cart cart = mapper.selectCartByCartId(cartId);

        if (cart == null || cart.getMemberId() != memberId) {
            throw new CustomException(CART_NOT_EXIST);
        }
    }
}
