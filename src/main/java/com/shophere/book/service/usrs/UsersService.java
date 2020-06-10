package com.shophere.book.service.usrs;

import com.shophere.book.api.dto.users.*;
import com.shophere.book.config.auth.JwtTokenProvider;
import com.shophere.book.domain.user.UserRepository;
import com.shophere.book.domain.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Long save(UserRegisterDto registerDto) {

        // 비밀번호 암호화
        String securityPassword = passwordEncoder.encode(registerDto.getPassword());
        registerDto.changeSecurityPassword(securityPassword);

        Users savedUser = userRepository.save(registerDto.toEntity());

        return savedUser.getId();
    }

    public UserSigninResponseDto signIn(UserSigninDto signinDto) {
        String email = signinDto.getEmail();
        Users findUser = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        // 패스워드 확인
        if (!passwordEncoder.matches(signinDto.getPassword(), findUser.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        String token = jwtTokenProvider.createToken(findUser.getUsername(), findUser.getRole());

        UserSigninResponseDto siginUser = UserSigninResponseDto.builder().accessToken(token).build();

        return siginUser;
    }

    @Transactional
    public String update(UserUpdateDto updateDto, String email) {
        Users findUser = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        // 패스워드 암호화
        String securityPassword = passwordEncoder.encode(updateDto.getPassword());
        updateDto.changeSecurityPassword(securityPassword);

        findUser.update(updateDto);

        return "Success Update";
    }

    @Transactional
    public Long delete(Long id) {
        Users findUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));
        userRepository.delete(findUser);

        return id;
    }

    public UserResponseDto findByEmail(String email) {
        Users findUser = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));
        return new UserResponseDto(findUser);
    }

    @Transactional
    public Long updateOwner(String email) {
        Users findUser = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));
        findUser.updateOwner();

        return findUser.getId();
    }

    public Boolean emailCheck(String email) {
        Optional<Users> byEmail = userRepository.findByEmail(email);
        return byEmail.isPresent();
    }
}
