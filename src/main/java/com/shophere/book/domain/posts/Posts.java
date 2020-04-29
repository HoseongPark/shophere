package com.shophere.book.domain.posts;

import com.shophere.book.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 250, nullable = true)
    private String overView;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String content;

    @Column
    private String author;

    @Builder
    public Posts(String title, String overView, String content, String author) {
        this.title = title;
        this.overView = overView;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content, String overView) {
        this.title = title;
        this.overView = overView;
        this.content = content;
    }
}
