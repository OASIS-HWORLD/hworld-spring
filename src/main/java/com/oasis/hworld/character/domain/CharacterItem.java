package com.oasis.hworld.character.domain;

import lombok.Data;

/**
 * 캐릭터 장착 상품 VO
 * @author 조영욱
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	조영욱        최초 생성
 * </pre>
 */
@Data
public class CharacterItem {
    // 회원 ID
    private int memberId;
    // 상품 카테고리 ID
    private int categoryId;
    // 상품 옵션 ID
    private int itemOptionId;
}
