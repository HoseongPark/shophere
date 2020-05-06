package com.shophere.book.domain.book;

import com.shophere.book.domain.BaseTimeEntity;
import com.shophere.book.domain.shops.Shops;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BookShop extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "books_id")
    private Books books;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shops_id")
    private Shops shops;

    // 연관관계 메소드
    public void setBooks(Books books) {
        this.books = books;
    }

    @Builder
    public BookShop(Shops shops) {
        this.shops = shops;
    }

    public static BookShop bookShopCreate(Shops shop) {
        BookShop bookShop = BookShop.builder()
                .shops(shop)
                .build();

        return bookShop;
    }
}