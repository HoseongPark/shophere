package com.shophere.book.api.dto.users;

import lombok.Getter;

@Getter
public class UserUpdateDto {
    private String password;

    public void changeSecurityPassword(String securityPassword) {
        this.password = securityPassword;
    }
}
