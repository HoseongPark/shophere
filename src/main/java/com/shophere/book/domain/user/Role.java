package com.shophere.book.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "guest"),
    USER("ROLE_USER", "user"),
    OWNER("ROLE_OWNER", "owner");

    private String key;
    private String title;
}
