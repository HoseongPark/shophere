package com.shophere.book.domain.shops;

import com.shophere.book.api.dto.shops.ShopsSaveRequestDto;
import com.shophere.book.domain.BaseTimeEntity;
import com.shophere.book.domain.book.BookShop;
import com.shophere.book.domain.user.Users;
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

    private Integer price;

    private String category;

    @OneToMany(mappedBy = "shops")
    private List<BookShop> booksShops = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @Builder
    public Shops(String title, String overView, String content, String author, Integer price, String category, Users user) {
        this.title = title;
        this.overView = overView;
        this.content = content;
        this.author = author;
        this.price = price;
        this.category = category;
        this.users = user;
    }

    public static Shops createShop(ShopsSaveRequestDto requestDto, Users user) {
        Shops shop = Shops.builder()
                .title(requestDto.getTitle())
                .overView(requestDto.getOverView())
                .author(requestDto.getAuthor())
                .content(requestDto.getContent())
                .price(requestDto.getPrice())
                .category(requestDto.getCategory())
                .user(user)
                .build();

        // 연관 관계 메서드 처리
        user.getShops().add(shop);

        return shop;
    }

    public void update(String title, String content, String overView, Integer price, String category) {
        this.title = title;
        this.content = content;
        this.overView = overView;
        this.price = price;
        this.category = category;
    }
}
