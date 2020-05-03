package com.shophere.book.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    BOOKING("booking", "예약 중"),
    FINISH("finish", "예약 완료");

    private final String key;
    private final String title;
}
