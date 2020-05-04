package com.shophere.book.api.controller;

import com.shophere.book.api.dto.books.BooksSaveRequestDto;
import com.shophere.book.service.books.BooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "BookApiController V1")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BooksService booksService;

    @ApiOperation(value = "Books 등록")
    @PostMapping("/books/")
    public Long booksSave(@RequestParam Long userId, @RequestParam Long shopId) {
        Long booksSaveId = booksService.booksSave(userId, shopId);
        return booksSaveId;
    }
}
