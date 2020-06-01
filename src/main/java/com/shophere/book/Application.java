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

    // 개발용 임시 Master 계정
    @PostConstruct
    public void masterUser() {
        UserRegisterDto registerDto = UserRegisterDto.builder()
                .name("master")
                .email("master@master.com")
                .password("master1234")
                .build();

        UserResponseDto masterUser = usersService.findByEmail(registerDto.getEmail());
        if (masterUser == null) {
            usersService.save(registerDto);
            usersService.updateOwner(registerDto.getEmail());
        } else {
            return;
        }

    }

}
