package com.oasis.hworld.shop.service;

import com.oasis.hworld.shop.domain.Shop;
import com.oasis.hworld.shop.dto.ItemDetailResponseDTO;
import com.oasis.hworld.shop.dto.ShopItemDTO;
import com.oasis.hworld.shop.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
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
 * 2024.09.10   조영욱        S3 도입으로 인한 이미지 URL 변경
 * 2024.09.14   김지현        아이템 상세 조회 메소드 추가
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopMapper mapper;
    @Value("${S3_BUCKET_URL}")
    private String s3BucketUrl;

    /**
     * 한 상점의 상품 목록 조회
     *
     * @author 정은찬
     */
    public List<ShopItemDTO> getShopOneItemList(int shopId, int categoryId) {
        List<ShopItemDTO> shopItemList = mapper.selectItemListByShopIdAndCategoryId(shopId, categoryId);

        // s3 버킷 이미지 url 추가
        for (ShopItemDTO item : shopItemList) {
            item.setItemImageUrl(s3BucketUrl + item.getItemImageUrl());
        }

        return shopItemList;
    }

    /**
     * 모든 상점의 상품 목록 조회
     *
     * @author 정은찬
     */
    public List<ShopItemDTO> getShopAllItemList(int categoryId) {
        List<ShopItemDTO> shopItemList = mapper.selectItemListByCategoryId(categoryId);

        // s3 버킷 이미지 url 추가
        for (ShopItemDTO item : shopItemList) {
            item.setItemImageUrl(s3BucketUrl + item.getItemImageUrl());
        }

        return shopItemList;
    }

    /**
     * 상점 목록 조회
     *
     * @author 정은찬
     */
    public List<Shop> getShopList() {
        List<Shop> shopList = mapper.selectShopList();

        // s3 버킷 이미지 url 추가
        for (Shop shop : shopList) {
            shop.setShopImageUrl(s3BucketUrl + shop.getShopImageUrl());
        }

        return shopList;
    }

    /**
     * 아이템 상세 조회
     *
     * @author 김지현
     */
    @Override
    public ItemDetailResponseDTO getItemDetail(int itemId) {
        ItemDetailResponseDTO itemDetail = mapper.selectItemDetailByItemId(itemId);

        // s3 버킷 이미지 url 추가
        itemDetail.setItemImageUrl(s3BucketUrl + itemDetail.getItemImageUrl());
        itemDetail.setShopImageUrl(s3BucketUrl + itemDetail.getShopImageUrl());
        itemDetail.setItemDetailImageUrl(s3BucketUrl + itemDetail.getItemDetailImageUrl());

        return itemDetail;
    }

}
