package com.shophere.book.service.usrs;

import com.shophere.book.api.dto.users.UserRegisterDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

    @Autowired
    UsersService usersService;

    @Test
    public void userSave() throws Exception {
        //Given
        UserRegisterDto registerDto = UserRegisterDto.builder()
                .name("test")
                .password("test")
                .email("test@test.com")
                .build();

        //When
        Long savedId = usersService.save(registerDto);

        //Then
        assertThat(savedId).isEqualTo(1L);
    }
}