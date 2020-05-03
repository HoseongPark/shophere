package com.shophere.book.api.dto.shops;

import com.shophere.book.domain.shops.Shops;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ShopsListResponseDto {
    private Long id;
    private String title;
    private String overView;
    private String author;
    private LocalDateTime modifiedDate;

    public ShopsListResponseDto(Shops shops) {
        this.id = shops.getId();
        this.overView = shops.getOverView();
        this.title = shops.getTitle();
        this.author = shops.getAuthor();
        this.modifiedDate = shops.getModifiedDate();
    }
}
