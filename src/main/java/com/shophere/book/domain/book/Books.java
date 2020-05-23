package com.shophere.book.domain.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shophere.book.domain.BaseTimeEntity;
import com.shophere.book.domain.user.Users;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Books extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "books_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    private LocalDate bookDate;

    private LocalTime bookTime;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<BookShop> bookShops = new ArrayList<>();

    // -- 연관관계 처리 -- //
    public void setUsers(Users user) {
        this.users = user;
        user.getBooks().add(this);
    }

    public void addBookShop(BookShop bookShop) {
        this.bookShops.add(bookShop);
        bookShop.setBooks(this);
    }

    // 예약상태 변경 메소드
    public void changeStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Builder
    public Books (Users user, LocalDate bookDate, LocalTime bookTime, BookStatus bookStatus) {
        this.users = user;
        this.bookDate = bookDate;
        this.bookTime = bookTime;
        this.bookStatus = bookStatus;
    }

    // 예약 생성 메소드
    public static Books createBook(Users users, LocalDate bookDate, LocalTime bookTime, BookShop... bookShop) {
        Books book = Books.builder()
                .bookDate(bookDate)
                .bookTime(bookTime)
                .bookStatus(BookStatus.BOOKING)
                .build();

        book.setUsers(users);
        for (BookShop shop : bookShop) {
            book.addBookShop(shop);
        }

        return book;
    }

    /**
     * 예약 취소 메서드
     */
    public void cancelBook() {
        changeStatus(BookStatus.CANCEL);
    }
}
