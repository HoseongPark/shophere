package com.shophere.book.api.dto.shops;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShopSearchCondition {

    private String name;
    private String category;
    private int priceGoe;
    private int priceLoe;

    public ShopSearchCondition createCondition(String name, String category, int priceGoe, int priceLoe) {
        ShopSearchCondition condition = ShopSearchCondition.builder()
                .name(name)
                .category(category)
                .priceGoe(priceGoe)
                .priceLoe(priceLoe)
                .build();

        return condition;
    }
}
