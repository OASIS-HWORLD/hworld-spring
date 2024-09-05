package com.oasis.hworld.shop.domain;

import lombok.Data;

/**
 * 상점 VO
 * @author 정은찬
 * @since 2024.09.04
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	정은찬        최초 생성
 * </pre>
 */
@Data
public class Shop {
    private int shopId;
    private String name;
    private String imageUrl;

}
