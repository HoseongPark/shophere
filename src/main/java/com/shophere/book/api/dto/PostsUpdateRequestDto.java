package com.shophere.book.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String overView;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String overView, String content) {
        this.title = title;
        this.overView = overView;
        this.content = content;
    }
}
