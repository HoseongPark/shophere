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
    private int price;

    @Builder
    public ShopsSaveRequestDto(String title, String overView, String content, String author, int price) {
        this.title = title;
        this.overView = overView;
        this.content = content;
        this.author = author;
        this.price = price;
    }

    public Shops toEntity() {
        return Shops.builder()
                .title(title)
                .overView(overView)
                .content(content)
                .author(author)
                .price(price)
                .build();
    }
}