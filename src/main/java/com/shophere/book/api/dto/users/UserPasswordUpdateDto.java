package com.shophere.book.api.dto.users;

import lombok.Getter;

@Getter
public class UserPasswordUpdateDto {
    private String oldPassword;
    private String newPassword;

    public void changeSecurityPassword(String securityPassword) {
        this.oldPassword = securityPassword;
        this.newPassword = securityPassword;
    }
}
