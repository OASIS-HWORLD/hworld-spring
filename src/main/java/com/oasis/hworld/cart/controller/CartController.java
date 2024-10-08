package com.oasis.hworld.cart.controller;

import com.oasis.hworld.cart.dto.CartItemOptionRequestDTO;
import com.oasis.hworld.cart.dto.CartListResponseDTO;
import com.oasis.hworld.cart.dto.ModifyCartItemCountRequestDTO;
import com.oasis.hworld.cart.service.CartService;
import com.oasis.hworld.common.annotation.MemberId;
import com.oasis.hworld.common.dto.CommonResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
 * 2024.09.03   조영욱        Item -> ItemOption 변경
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
     * @apiNote 로그인한 회원의 장바구니를 조회한다.
     */
    @GetMapping("")
    public ResponseEntity<CartListResponseDTO> getCartList(@MemberId int memberId) {
        return ResponseEntity.ok(service.getCartList(memberId));
    }

    /**
     * 장바구니에 상품 추가
     *
     * @author 조영욱
     */
    @PostMapping("")
    public ResponseEntity<CommonResponseDTO> addCart(@RequestBody CartItemOptionRequestDTO dto, @MemberId int memberId) {
        return service.addCart(dto, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "장바구니에 추가되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "이미 장바구니에 추가된 상품입니다."));
    }

    /**
     * 장바구니에서 상품 삭제
     *
     * @author 조영욱
     */
    @DeleteMapping("/{cartId}")
    public ResponseEntity<CommonResponseDTO> removeItemFromCart(@PathVariable("cartId") int cartId, @MemberId int memberId) {
        return service.removeItemFromCart(cartId, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "장바구니에서 상품이 삭제되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "장바구니에 상품이 존재하지 않습니다."));
    }

    /**
     * 장바구니의 상품 개수 변경
     *
     * @author 조영욱
     */
    @PutMapping("/count")
    public ResponseEntity<CommonResponseDTO> modifyCartItemCount(@RequestBody ModifyCartItemCountRequestDTO dto, @MemberId int memberId) {
        return service.modifyCartItemCount(dto, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "수량이 변경되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "장바구니에 상품이 존재하지 않습니다."));
    }
}
