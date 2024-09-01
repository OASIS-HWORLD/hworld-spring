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
public class ItemsDTO {
    // 안경 ID
    private int glassesId;
    // 안경 이름
    private String glassesName;
    // 안경 가게 이름
    private String glassesShopName;
    // 안경 이미지 url
    private String glassesImageUrl;
    // 모자 ID
    private int capId;
    // 모자 이름
    private String capName;
    // 모자 가게 이름
    private String capShopName;
    // 모자 이미지 url
    private String capImageUrl;
    // 가방  ID
    private int bagId;
    // 가방 이름
    private String bagName;
    // 가방 가게 이름
    private String bagShopName;
    // 가방 이미지 url
    private String bagImageUrl;
    // 목걸이 ID
    private int necklaceId;
    // 목걸이 이름
    private String necklaceName;
    // 목걸이 가게 이름
    private String necklaceShopName;
    // 목걸이 이미지 url
    private String necklaceImageUrl;
}
