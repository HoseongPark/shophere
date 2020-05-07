package com.shophere.book.api.dto.books;

import com.shophere.book.domain.book.BookStatus;
import com.shophere.book.domain.book.Books;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 회원 ID로 예약 조회 결과 DTO
 */
@Data
public class BooksResponseDto {

    private Long bookId;
    private String name;
    private LocalDate bookDate;
    private LocalTime bookTime;
    private BookStatus bookStatus;
    private String shopTitle;
    private String shopOverView;
    private String shopContent;

    public BooksResponseDto(Books book) {
        this.bookId = book.getId();
        this.name = book.getUsers().getName();
        this.bookDate = book.getBookDate();
        this.bookTime = book.getBookTime();
        this.bookStatus = book.getBookStatus();
        this.shopTitle = book.getBookShops().get(0).getShops().getTitle();
        this.shopOverView = book.getBookShops().get(0).getShops().getOverView();
        this.shopContent = book.getBookShops().get(0).getShops().getContent();
    }
}
