package com.oasis.hworld.shop.service;

import com.oasis.hworld.shop.dto.ShopItemDTO;

import java.util.List;

/**
 * 상점 서비스 인터페이스
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
public interface ShopService {
    /**
     * 한 상점의 상품 목록 조회
     *
     * @author 정은찬
     */
    List<ShopItemDTO> getShopOneItemList(int shopId, int categoryId);
}
