package com.shophere.book.api.dto.users;

import com.shophere.book.domain.user.Users;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String email;
    private String name;
    private String picture;

    public UserResponseDto(Users users) {
        this.email = users.getEmail();
        this.name = users.getName();
        this.picture = users.getPicture();
    }
}
