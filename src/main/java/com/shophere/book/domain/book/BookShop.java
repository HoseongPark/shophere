package com.shophere.book.domain.book;

import com.shophere.book.domain.BaseTimeEntity;
import com.shophere.book.domain.shops.Shops;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public BookShop(Books books, Shops shops) {
        this.shops = shops;
    }

    public static BookShop bookShopCreate(Shops shops) {
        BookShop bookShop = BookShop
                .builder()
                .shops(shops)
                .build();

        return bookShop;
    }
}