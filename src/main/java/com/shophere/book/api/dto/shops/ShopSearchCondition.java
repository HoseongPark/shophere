package com.shophere.book.api.dto.shops;

import lombok.*;

@AllArgsConstructor
@Getter
public class ShopSearchCondition {

    private String title;
    private String category;
    private Integer priceGoe;
    private Integer priceLoe;
}