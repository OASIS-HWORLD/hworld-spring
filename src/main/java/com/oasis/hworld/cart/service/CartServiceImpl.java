package com.oasis.hworld.cart.service;

import com.oasis.hworld.cart.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

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

    @Override
    public int getCartList(int memberId) {
        mapper.selectCartByMemberId(memberId);
        log.info("서비스 잘 됨.");
        return 0;
    }
}
