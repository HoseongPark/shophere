package com.shophere.book.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor  // final field의 생성자를 생성 해주는 어노테이션
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
