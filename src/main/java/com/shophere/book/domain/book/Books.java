package com.shophere.book.domain.book;

import com.shophere.book.domain.BaseTimeEntity;
import com.shophere.book.domain.user.Users;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Books extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "books_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    private LocalDate bookDate;

    private LocalTime bookTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "books")
    private List<BookShop> bookShops = new ArrayList<>();

    public void addBookShop(BookShop bookShop) {
        this.bookShops.add(bookShop);
    }

    @Builder
    public Books(Users users, LocalDate bookDate, LocalTime bookTime, Status status, BookShop... bookShops) {
        this.users = users;
        this.bookDate = bookDate;
        this.bookTime = bookTime;
        this.status = status;
        for (BookShop bookShop : bookShops) {
            addBookShop(bookShop);
        }
    }

    public static Books createBook(Users users, LocalDate bookDate, LocalTime bookTime, BookShop... bookShop) {
        Books books = Books.builder()
                .users(users)
                .bookDate(bookDate)
                .bookTime(bookTime)
                .bookShops(bookShop)
                .status(Status.BOOKING)
                .build();

        return books;
    }
}
