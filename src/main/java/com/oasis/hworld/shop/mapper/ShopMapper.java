package com.oasis.hworld.shop.mapper;

import com.oasis.hworld.shop.dto.ShopItemDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 상점 Mybatis 인터페이스
 * @author 정은찬
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------
 * 2024.09.03   정은찬        최초 생성
 * </pre>
 */
public interface ShopMapper {

    // 상점 ID와 카테고리 ID를 통한 아이템 리스트 조회
    List<ShopItemDTO> selectItemListByShopIdAndCategoryId(@Param("shopId") int shopId, @Param("categoryId") int categoryId);

    // 카테고리 ID를 통한 아이템 리스트 조회
    List<ShopItemDTO> selectItemListByCategoryId(@Param("categoryId") int categoryId);
}
