package com.shophere.book.api.dto.shops;

import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.shophere.book.domain.shops.Shops;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopsResponseDto {
    private Long id;
    private String title;
    private String overView;
    private String content;
    private String author;
    private Integer price;
    private String category;

    public ShopsResponseDto(Shops shops) {
        this.id = shops.getId();
        this.title = shops.getTitle();
        this.overView = shops.getOverView();
        this.content = shops.getContent();
        this.author = shops.getAuthor();
        this.price = shops.getPrice();
        this.category = shops.getCategory();
    }
}
