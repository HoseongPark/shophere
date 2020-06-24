package com.shophere.book.api.controller;

import com.shophere.book.api.dto.shops.ShopsSaveRequestDto;
import com.shophere.book.api.dto.shops.ShopsUpdateRequestDto;
import com.shophere.book.service.shops.ShopsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "OwnersController V1")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class OwnersApiController {
    private final ShopsService shopsService;

    // 등록
    @ApiOperation(value = "상점 등록")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @PostMapping("/shops")
    public String save(@RequestBody ShopsSaveRequestDto requestDto) {
        return shopsService.save(requestDto);
    }

    // 수정
    @ApiOperation(value = "상점 수정")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @PutMapping("/shops/{id}")
    public Long update(
            @PathVariable("id") Long id,
            @RequestBody ShopsUpdateRequestDto requestDto) {
        return shopsService.update(id, requestDto);
    }

    // 게시물 삭제
    @ApiOperation(value = "상점 삭제")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @DeleteMapping("/shops/{id}")
    public Long delete(@PathVariable Long id) {
        shopsService.delete(id);
        return id;
    }
}
