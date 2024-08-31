package com.oasis.hworld.cart.controller;

import com.oasis.hworld.cart.dto.GetCartListResponseDTO;
import com.oasis.hworld.cart.service.CartService;
import com.oasis.hworld.common.dto.CommonResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 장바구니 컨트롤러
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
@RestController
@RequestMapping(value="/carts", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    /**
     * 회원 장바구니 조회
     *
     * @author 조영욱
     * @apiNote 로그인 한 회원의 장바구니를 조회한다.
     */
    @GetMapping("")
    public ResponseEntity<GetCartListResponseDTO> getCartList() {
        return ResponseEntity.ok(service.getCartList(1));
    }
}
