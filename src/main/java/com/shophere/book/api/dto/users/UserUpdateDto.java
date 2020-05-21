package com.shophere.book.api.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserUpdateDto {
    private String password;

    public void changeSecurityPassword(String securityPassword) {
        this.password = securityPassword;
    }
}
