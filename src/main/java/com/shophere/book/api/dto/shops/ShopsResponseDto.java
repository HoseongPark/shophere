package com.shophere.book.api.dto.shops;

import com.shophere.book.domain.shops.Shops;
import lombok.Getter;

@Getter
public class ShopsResponseDto {
    private Long id;
    private String title;
    private String overView;
    private String content;
    private String author;

    public ShopsResponseDto(Shops shops) {
        this.id = shops.getId();
        this.title = shops.getTitle();
        this.overView = shops.getOverView();
        this.content = shops.getContent();
        this.author = shops.getAuthor();
    }
}
