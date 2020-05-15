package com.shophere.book.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Role  {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자"),
    OWNER("ROLE_OWNER", "가게 사용자");

    private final String key;
    private final String title;
}
