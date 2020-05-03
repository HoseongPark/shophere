package com.shophere.book.service.books;

import com.shophere.book.domain.book.BookShop;
import com.shophere.book.domain.book.Books;
import com.shophere.book.domain.book.BooksRepository;
import com.shophere.book.domain.book.Status;
import com.shophere.book.domain.shops.Shops;
import com.shophere.book.domain.shops.ShopsRepository;
import com.shophere.book.domain.user.UserRepository;
import com.shophere.book.domain.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BooksService {

    private final BooksRepository booksRepository;
    private final ShopsRepository shopsRepository;
    private final UserRepository userRepository;

    // 예약 하기 ( 등록하기 )
    @Transactional
    public Long booksSave(Long usersId, Long shopsId) {
        Users user = userRepository.findById(usersId).orElseThrow(() -> new IllegalArgumentException("없어요~"));
        Shops shop = shopsRepository.findById(shopsId).orElseThrow(() -> new IllegalArgumentException("없어요~"));

        // 예약 상점 생성
        BookShop bookShop = BookShop.bookShopCreate(shop);

        // 예약 생성
        Books books = Books.createBook(user, LocalDate.now(), LocalTime.now(), bookShop);

        booksRepository.save(books);

        return books.getId();
    }
    // 예약 변경하기 (수정하기)
    // 예약 취소하기 (삭제하기)
}
