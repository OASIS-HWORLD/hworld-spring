package com.oasis.hworld.shop.controller;

import com.oasis.hworld.contest.dto.PostSummaryDTO;
import com.oasis.hworld.shop.dto.ShopItemDTO;
import com.oasis.hworld.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/one")
    public ResponseEntity<List<ShopItemDTO>> getShopOneItemList(@RequestParam("shopId") int shopId, @RequestParam("categoryId") int categoryId) {
        return ResponseEntity.ok(service.getShopOneItemList(shopId, categoryId));
    }

    /**
     * 모든 상점의 상품 목록 조회
     *
     * @author 정은찬
     * @apiNote 한 상점의 상품 목록을 조회한다.
     */
    @GetMapping("/all")
    public ResponseEntity<List<ShopItemDTO>> getShopOneItemList(@RequestParam("categoryId") int categoryId) {
        return ResponseEntity.ok(service.getShopAllItemList(categoryId));
    }
}
