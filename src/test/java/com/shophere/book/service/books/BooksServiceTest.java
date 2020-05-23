package com.shophere.book.service.books;

import com.shophere.book.domain.book.Books;
import com.shophere.book.domain.book.BooksRepository;
import com.shophere.book.domain.shops.Shops;
import com.shophere.book.domain.shops.ShopsRepository;
import com.shophere.book.domain.user.UserRepository;
import com.shophere.book.domain.user.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.shophere.book.domain.book.BookStatus.CANCEL;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShopsRepository shopsRepository;

    @Autowired
    BooksService booksService;

    @Autowired
    BooksRepository booksRepository;

    @Before
    public void setUp() throws Exception {
        Users user = Users.builder()
                .name("hoseong")
                .email("hoseong")
                .password("hoseong")
                .build();

        userRepository.save(user);

        Shops shop = Shops.builder()
                .author("hoseong")
                .content("hoseong")
                .title("hoseong")
                .overView("hoseong")
                .build();

        shopsRepository.save(shop);
    }

    @Test
    public void cancelTest() throws Exception {

        //Given
        booksService.booksSave(1L, 1L);

        //When
        booksService.booksDelete(1L);

        //Then
        Books findBook = booksRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("없어요"));
        assertThat(findBook.getBookStatus()).isEqualTo(CANCEL);
    }

}