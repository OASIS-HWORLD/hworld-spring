package com.oasis.hworld.contest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 게시글 코디 착용 아이템 DTO
 * @author 정은찬
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	정은찬        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
public class ItemDTO {
    private int itemId;
    private String itemName;
    private String shopName;
    private String itemImageUrl;
    private String itemType;
}
