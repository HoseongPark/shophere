package com.shophere.book.api.controller;

import com.shophere.book.api.dto.books.BooksSaveRequestDto;
import com.shophere.book.domain.book.Books;
import com.shophere.book.service.books.BooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "BookApiController V1")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BooksService booksService;

    @ApiOperation(value = "Books 등록")
    @PostMapping("/books/")
    public Long booksSave(@RequestParam Long userId, @RequestParam Long shopId) {
        Long saveBookId = booksService.booksSave(userId, shopId);
        return saveBookId;
    }

    @ApiOperation(value = "Books 삭제")
    @DeleteMapping("/books/{id}")
    public Long booksDelete(@PathVariable("id") Long bookId) {
        Long deleteBookId = booksService.booksDelete(bookId);
        return deleteBookId;
    }

    @ApiOperation(value = "Books 조회")
    @GetMapping("/books/{id}")
    public List<Books> booksRead(@PathVariable("id") Long userId) {
        List<Books> books = booksService.booksRead(userId);

        return books;
    }
}
