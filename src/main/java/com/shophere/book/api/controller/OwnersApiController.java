package com.shophere.book.api.controller;

import com.shophere.book.api.dto.shops.ShopsResponseDto;
import com.shophere.book.api.dto.shops.ShopsSaveRequestDto;
import com.shophere.book.api.dto.shops.ShopsUpdateRequestDto;
import com.shophere.book.service.shops.ShopsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "OwnersController V1")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class OwnersApiController {
    private final ShopsService shopsService;

    // 등록
    @ApiOperation(value = "상점 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header"),
    })
    @PostMapping("/owners")
    public String save(@RequestBody ShopsSaveRequestDto requestDto) {
        return shopsService.save(requestDto);
    }

    // 수정
    @ApiOperation(value = "상점 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header"),
            @ApiImplicitParam(name="id", required = true, dataType = "Long", value = "상점 Index")
    })
    @PutMapping("/owners/{id}")
    public Long update(
            @PathVariable("id") Long id,
            @RequestBody ShopsUpdateRequestDto requestDto) {
        return shopsService.update(id, requestDto);
    }

    // 게시물 삭제
    @ApiOperation(value = "상점 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header"),
            @ApiImplicitParam(name="id", required = true, dataType = "Long", value = "상점 Index")
    })
    @DeleteMapping("/owners/{id}")
    public Long delete(@PathVariable Long id) {
        shopsService.delete(id);
        return id;
    }

    // 상점 리스트 조회
    @ApiOperation(value = "상점 리스트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header"),
            @ApiImplicitParam(name = "id", required = true, dataType = "Long", value = "회원 ID"),
    })
    @GetMapping("/owners/{id}")
    public List<ShopsResponseDto> findShops(@PathVariable Long id) {
        List<ShopsResponseDto> result = shopsService.findByUser(id);
        return result;
    }
}
