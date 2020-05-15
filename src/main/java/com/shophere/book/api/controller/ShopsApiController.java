package com.shophere.book.api.controller;

import com.shophere.book.service.shops.ShopsService;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import com.shophere.book.api.dto.shops.ShopsSaveRequestDto;
import com.shophere.book.api.dto.shops.ShopsUpdateRequestDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "ShopsController V1")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ShopsApiController {

    private final ShopsService shopsService;

    // 등록
    @ApiOperation(value = "가맹점 등록")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @PostMapping("/shops")
    public Long save(@RequestBody ShopsSaveRequestDto requestDto) {
        return shopsService.save(requestDto);
    }

    // 수정
    @ApiOperation(value = "가맹점 수정")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @PutMapping("/shops/{id}")
    public Long update(
            @PathVariable("id") Long id,
            @RequestBody ShopsUpdateRequestDto requestDto) {
        return shopsService.update(id, requestDto);
    }

    // 상세 조회
    @ApiOperation(value = "가맹점 상세 조회")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @GetMapping("/shops/{id}")
    public ShopsResponseDto findById (@PathVariable Long id) {
        return shopsService.findById(id);
    }

    // 게시물 삭제
    @ApiOperation(value = "가맹점 삭제")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @DeleteMapping("/shops/{id}")
    public Long delete(@PathVariable Long id) {
        shopsService.delete(id);
        return id;
    }
}
