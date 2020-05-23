package com.shophere.book.service.books;

import com.shophere.book.api.dto.books.BooksResponseDto;
import com.shophere.book.domain.book.BookShop;
import com.shophere.book.domain.book.Books;
import com.shophere.book.domain.book.BooksRepository;
import com.shophere.book.domain.shops.Shops;
import com.shophere.book.domain.shops.ShopsRepository;
import com.shophere.book.domain.user.UserRepository;
import com.shophere.book.domain.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BooksService {

    private final BooksRepository booksRepository;
    private final ShopsRepository shopsRepository;
    private final UserRepository userRepository;

    /**
    *  예약하기 (등록하기)
    * */
    @Transactional
    public Long booksSave(Long usersId, Long shopsId) {
        Users user = userRepository.findById(usersId).orElseThrow(() -> new IllegalArgumentException("유저가 없어요~"));
        Shops shop = shopsRepository.findById(shopsId).orElseThrow(() -> new IllegalArgumentException("가맹점이 없어요~"));

        // 예약 상점 생성
        BookShop bookShop = BookShop.bookShopCreate(shop);

        // 예약 생성
        Books books = Books.createBook(user, LocalDate.now(), LocalTime.now(), bookShop);

        Books saveBooks = booksRepository.save(books);

        return saveBooks.getId();
    }

    /**
    *  예약 취소하기 (삭제하기)
    * */
    @Transactional
    public Long booksDelete(Long bookId) {

        Books findBook = booksRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("예약이 없어요..."));

        findBook.cancelBook();

        return findBook.getId();
    }

    /**
     * 예약 조회하기 (회원 ID 예약 조회하기)
     */
    public List<BooksResponseDto> booksReadByUserId(Long userId) {
        Users findUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));
        List<Books> findBooks = booksRepository.findByUser(findUser);

        List<BooksResponseDto> result = findBooks.stream()
                .map(o -> new BooksResponseDto(o))
                .collect(Collectors.toList());

        return result;
    }
}
