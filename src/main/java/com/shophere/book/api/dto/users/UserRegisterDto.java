package com.shophere.book.api.dto.users;

import com.shophere.book.domain.user.Role;
import com.shophere.book.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;

@Getter
@NoArgsConstructor
public class UserRegisterDto {

    private String name;
    private String password;
    private String email;

    @Builder
    public UserRegisterDto(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public void changeSecurityPassword(String securityPassword) {
        this.password = securityPassword;
    }

    public Users toEntity() {
        Users user = Users.builder()
                .name(name)
                .email(email)
                .password(password)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        return user;
    }
}
