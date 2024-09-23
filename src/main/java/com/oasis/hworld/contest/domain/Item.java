package com.oasis.hworld.contest.domain;

import lombok.Data;

@Data
public class Item {
    private int itemId;
    private int shopId;
    private int categoryId;
    private String name;
    private int price;
    private String imageUUrl;
    private String detailImageUrl;
}
