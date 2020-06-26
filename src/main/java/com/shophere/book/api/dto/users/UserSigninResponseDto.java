package com.shophere.book.api.dto.users;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSigninResponseDto {

    private String accessToken;
    private Long userId;
}
