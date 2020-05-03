package com.shophere.book.domain.book;

import com.shophere.book.domain.shops.Shops;
import com.shophere.book.domain.shops.ShopsRepository;
import com.shophere.book.domain.user.Role;
import com.shophere.book.domain.user.UserRepository;
import com.shophere.book.domain.user.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BooksRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShopsRepository shopsRepository;

    @Autowired
    BooksRepository booksRepository;

    @Test
    @Rollback(false)
    public void saveTest() throws Exception {

        //given
        Users users = Users.builder()
                .name("hoseong")
                .picture("xxx")
                .email("hoseong@hoseong.com")
                .role(Role.GUEST)
                .build();

        Shops shops = Shops.builder()
                .title("hoseong shop")
                .overView("first hoseong shop")
                .content("first hoseong shop")
                .author("hoseong")
                .build();

        Shops shops1 = Shops.builder()
                .title("hoseong shop2")
                .overView("first hoseong shop2")
                .content("first hoseong shop2")
                .author("hoseong2")
                .build();

        //when
        userRepository.save(users);
        shopsRepository.save(shops);
        shopsRepository.save(shops1);

        //then
        List<Users> findUser = userRepository.findAll();
        List<Shops> findShop = shopsRepository.findAll();

        BookShop bookShop = BookShop.bookShopCreate(shops);
        BookShop bookShop1 = BookShop.bookShopCreate(shops1);

        List<BookShop> listShop = new ArrayList<>();
        listShop.add(bookShop);
        listShop.add(bookShop1);

        Books books = Books.createBook(findUser.get(0), LocalDate.now(), LocalTime.now(), listShop);

        booksRepository.save(books);

        List<Books> findBooks = booksRepository.findAll();

        assertThat(findUser.get(0).getName()).isEqualTo("hoseong");
        assertThat(findShop.get(0).getTitle()).isEqualTo("hoseong shop");
        assertThat(findBooks.get(0).getId()).isEqualTo(1L);

        Books findBook = findBooks.get(0);
        List<BookShop> list = findBook.getBookShops();

        for (BookShop shop : list) {
            System.out.println(shop.getShops().getTitle());
        }
    }
}