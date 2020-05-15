package com.shophere.book.api.controller;

import com.shophere.book.api.dto.users.UserRegisterDto;
import com.shophere.book.service.usrs.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "UsersController V1")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class UsersApiController {

    private final UsersService usersService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/users/")
    public Long userSave(@RequestBody UserRegisterDto registerDto) {
        Long savedId = usersService.save(registerDto);
        return savedId;
    }

    @ApiOperation(value = "로그인")
    @GetMapping("/users/signin")
    public String userLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
        String token = usersService.userLogin(email, password);
        return token;
    }
}
