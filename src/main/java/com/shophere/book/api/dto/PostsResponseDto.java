package com.shophere.book.api.dto;

import com.shophere.book.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String overView;
    private String content;
    private String author;

    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.overView = posts.getOverView();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }
}
