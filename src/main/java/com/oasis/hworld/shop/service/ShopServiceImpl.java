package com.oasis.hworld.shop.service;

import com.oasis.hworld.common.domain.ItemCategory;
import com.oasis.hworld.shop.domain.Shop;
import com.oasis.hworld.shop.dto.ShopItemDTO;
import com.oasis.hworld.shop.dto.ShopResponseDTO;
import com.oasis.hworld.shop.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 상점 서비스 구현체
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
@Service
@Log4j
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopMapper mapper;

    /**
     * 한 상점의 상품 목록 조회
     *
     * @author 정은찬
     */
    public ShopResponseDTO getShopOneItemList(int shopId, int categoryId) {
        List<ShopItemDTO> shopItemList = mapper.selectItemListByShopIdAndCategoryId(shopId, categoryId);
        ShopResponseDTO shopResponseDTO = new ShopResponseDTO();
        shopResponseDTO.setItems(shopItemList);
        return shopResponseDTO;
    }

    /**
     * 모든 상점의 상품 목록 조회
     *
     * @author 정은찬
     */
    public List<ShopItemDTO> getShopAllItemList(int categoryId) {
        List<ShopItemDTO> shopItemList = mapper.selectItemListByCategoryId(categoryId);

        return shopItemList;
    }

    /**
     * 상점 목록 조회
     *
     * @author 정은찬
     */
    public List<Shop> getShopList() {
        return mapper.selectShopList();
    }

}
