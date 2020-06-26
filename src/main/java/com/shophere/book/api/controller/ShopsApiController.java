package com.shophere.book.api.controller;

import com.shophere.book.api.dto.shops.ShopSearchCondition;
import com.shophere.book.service.shops.ShopsService;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import com.shophere.book.api.dto.shops.ShopsSaveRequestDto;
import com.shophere.book.api.dto.shops.ShopsUpdateRequestDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "ShopsController V1")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ShopsApiController {

    private final ShopsService shopsService;

    // 상세 조회
    @ApiOperation(value = "상점 상세 조회")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @GetMapping("/shops/{id}")
    public ShopsResponseDto findById (@PathVariable Long id) {
        return shopsService.findById(id);
    }


    // 상점 조회
    @ApiOperation(value = "상점 조회")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", required = false, dataType = "String", value = "카테고리"),
            @ApiImplicitParam(name = "title", required = false, dataType = "String", value = "제목"),
            @ApiImplicitParam(name = "priceGoe", required = false, dataType = "String", value = "검색 가격보다 큰것"),
            @ApiImplicitParam(name = "priceLoe", required = false, dataType = "String", value = "검색 가격보다 작은것")
    })
    @GetMapping("/shops")
    public Page<ShopsResponseDto> findByCondition (ShopSearchCondition shopSearchCondition, Pageable pageable) {
        return shopsService.findByCondition(shopSearchCondition, pageable);
    }
}
