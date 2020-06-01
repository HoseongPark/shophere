package com.shophere.book;

import com.shophere.book.api.dto.users.UserRegisterDto;
import com.shophere.book.api.dto.users.UserResponseDto;
import com.shophere.book.domain.user.Role;
import com.shophere.book.domain.user.UserRepository;
import com.shophere.book.domain.user.Users;
import com.shophere.book.service.usrs.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final UsersService usersService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
