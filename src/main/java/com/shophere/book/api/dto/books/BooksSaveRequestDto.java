package com.shophere.book.api.dto.books;

import lombok.Getter;

/**
 * 예약 등록 요청 DTO
 */
@Getter
public class BooksSaveRequestDto {

    private Long userId;
    private Long shopId;

}
