package com.shophere.book.api.dto.shops;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopsUpdateRequestDto {
    private String title;
    private String overView;
    private String content;

    @Builder
    public ShopsUpdateRequestDto(String title, String overView, String content) {
        this.title = title;
        this.overView = overView;
        this.content = content;
    }
}
