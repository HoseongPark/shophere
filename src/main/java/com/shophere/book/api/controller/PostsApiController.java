package com.shophere.book.api.controller;

import com.shophere.book.service.posts.PostsService;
import com.shophere.book.api.dto.PostsResponseDto;
import com.shophere.book.api.dto.PostsSaveRequestDto;
import com.shophere.book.api.dto.PostsUpdateRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "PostsController V1")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // 등록
    @ApiOperation(value = "게시물 등록")
    @PostMapping("/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    // 수정
    @ApiOperation(value = "게시물 수정")
    @PutMapping("/posts/{id}")
    public Long update(
            @PathVariable("id") Long id,
            @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    // 상세 조회
    @ApiOperation(value = "게시물 상세 조회")
    @GetMapping("/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    // 게시물 삭제
    @ApiOperation(value = "게시물 삭제")
    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
