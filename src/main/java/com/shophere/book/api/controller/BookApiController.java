package com.shophere.book.api.controller;

import com.shophere.book.api.dto.books.BooksResponseDto;
import com.shophere.book.api.dto.books.BooksSaveRequestDto;
import com.shophere.book.domain.book.Books;
import com.shophere.book.service.books.BooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

    @ApiOperation(value = "예약 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "회원 ID"),
            @ApiImplicitParam(name = "shopId", value = "가맹점 ID")
    })
    @PostMapping("/books/")
    public Long booksSave(@RequestParam Long userId, @RequestParam Long shopId) {
        Long saveBookId = booksService.booksSave(userId, shopId);
        return saveBookId;
    }

    @ApiOperation(value = "예약 삭제")
    @ApiImplicitParam(name="id", value = "예약 ID", required = true)
    @DeleteMapping("/books/{id}")
    public Long booksDelete(@PathVariable("id") Long bookId) {
        Long deleteBookId = booksService.booksDelete(bookId);
        return deleteBookId;
    }

    @ApiOperation(value = "예약 조회")
    @ApiImplicitParam(name = "id", value = "회원 ID", required = true)
    @GetMapping("/books/{id}")
    public List<BooksResponseDto> booksRead(@PathVariable("id") Long userId) {
        List<BooksResponseDto> result = booksService.booksReadByUserId(userId);

        return result;
    }
}
