package com.shophere.book.domain.shops;

import com.shophere.book.domain.BaseTimeEntity;
import com.shophere.book.domain.book.BookShop;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Shops extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shops_id")
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 250, nullable = true)
    private String overView;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String content;

    private String author;

    @OneToMany(mappedBy = "shops")
    List<BookShop> booksShops = new ArrayList<>();



    @Builder
    public Shops(String title, String overView, String content, String author) {
        this.title = title;
        this.overView = overView;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content, String overView) {
        this.title = title;
        this.content = content;
        this.overView = overView;
    }
}
