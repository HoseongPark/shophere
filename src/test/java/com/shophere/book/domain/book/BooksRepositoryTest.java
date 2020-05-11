package com.shophere.book.domain.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksRepositoryTest {

    @Autowired
    BooksRepository booksRepository;

    @Test
    public void findByUser() throws Exception {
        //Given

        //When

        //Then
    }

}