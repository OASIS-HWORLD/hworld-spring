package com.oasis.hworld.shop.controller;

import com.oasis.hworld.contest.dto.PostSummaryDTO;
import com.oasis.hworld.shop.domain.Shop;
import com.oasis.hworld.shop.dto.ShopItemDTO;
import com.oasis.hworld.shop.dto.ShopResponseDTO;
import com.oasis.hworld.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 상점 컨트롤러
 * @author 정은찬
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------
 * 2024.09.03  	정은찬        최초 생성
 * 2024.09.04   정은찬        상점 목록 조회 메소드 추가
 * </pre>
 */
@RestController
@RequestMapping(value="/shop", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class ShopController {

    private final ShopService service;

    /**
     * 한 상점의 상품 목록 조회
     *
     * @author 정은찬
     * @apiNote 한 상점의 상품 목록을 조회한다.
     */
    @GetMapping("/item")
    public ResponseEntity<ShopResponseDTO> getShopOneItemList(@RequestParam("shopId") int shopId, @RequestParam("categoryId") int categoryId) {
        return ResponseEntity.ok(service.getShopOneItemList(shopId, categoryId));
    }

    /**
     * 모든 상점의 상품 목록 조회
     *
     * @author 정은찬
     * @apiNote 한 상점의 상품 목록을 조회한다.
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ShopItemDTO>> getShopOneItemList(@PathVariable int categoryId) {
        return ResponseEntity.ok(service.getShopAllItemList(categoryId));
    }

    /**
     * 상점 목록 조회
     *
     * @author 정은찬
     * @apiNote 모든 상점 목록을 조회한다.
     */
    @GetMapping("")
    public ResponseEntity<List<Shop>> getShopList() {
        return ResponseEntity.ok(service.getShopList());
    }

}
