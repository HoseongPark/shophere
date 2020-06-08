package com.shophere.book.api.controller;

import com.shophere.book.api.dto.users.*;
import com.shophere.book.service.usrs.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @PostMapping("/users")
    public Long register(@RequestBody UserRegisterDto registerDto) {
        Long savedId = usersService.save(registerDto);
        return savedId;
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/users/signin")
    public UserSigninResponseDto signin(@RequestBody UserSigninDto signinDto) {
        UserSigninResponseDto token = usersService.signIn(signinDto);
        return token;
    }

    @ApiOperation(value = "회원 수정")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @PutMapping("/users")
    public Long update(@RequestBody UserUpdateDto updateDto, @RequestParam("id") Long id) {
        Long updateUserId = usersService.update(updateDto, id);
        return updateUserId;
    }

    @ApiOperation(value = "회원 삭제")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @DeleteMapping("/users")
    public Long delete(@RequestParam("id") Long id) {
        Long deleteUserId = usersService.delete(id);
        return deleteUserId;
    }

    @ApiOperation(value = "회원 조회")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @GetMapping("/users")
    public UserResponseDto findUser(@RequestParam("Email") String email) {
        UserResponseDto findUser = usersService.findByEmail(email);
        return findUser;
    }

    @ApiOperation(value = "Owner로 권한 상승")
    @ApiImplicitParam(name="Authorization", required = true, dataType = "String", value = "인증 토큰", paramType = "header")
    @PutMapping("/users/authority/{email}")
    public Long updateOwner(@PathVariable("email") String email) {
        Long userId = usersService.updateOwner(email);
        return userId;
    }

    @ApiOperation(value = "Email 중복 체크")
    @ApiImplicitParam(name="email", required = true, dataType = "String", value = "이메일")
    @GetMapping("/users/authority")
    public Boolean emailCheck(@RequestParam("email") String email) {
        Boolean result = usersService.emailCheck(email);

        return result;
    }

}
