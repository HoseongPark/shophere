package com.shophere.book.api.dto;

import com.shophere.book.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String overView;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String overView, String content, String author) {
        this.title = title;
        this.overView = overView;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}