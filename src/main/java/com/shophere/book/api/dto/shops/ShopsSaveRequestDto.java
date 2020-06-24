package com.shophere.book.api.dto.shops;

import com.shophere.book.domain.shops.Shops;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopsSaveRequestDto {
    private String title;
    private String overView;
    private String content;
    private String author;
    private Integer price;
    private String category;
    private String email;
}