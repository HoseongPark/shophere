package com.shophere.book.service.usrs;

import com.shophere.book.api.dto.users.UserRegisterDto;
import com.shophere.book.api.dto.users.UserUpdateDto;
import com.shophere.book.config.auth.JwtTokenProvider;
import com.shophere.book.domain.user.UserRepository;
import com.shophere.book.domain.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long save(UserRegisterDto registerDto) {

        // 비밀번호 암호화
        String securityPassword = passwordEncoder.encode(registerDto.getPassword());
        registerDto.changeSecurityPassword(securityPassword);

        Users savedUser = userRepository.save(registerDto.toEntity());

        return savedUser.getId();
    }

    public String userLogin(String email, String password) {
        Users findUser = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        // 패스워드 확인
        if (!passwordEncoder.matches(password, findUser.getPassword())) {
            throw new RuntimeException();
        }
        return jwtTokenProvider.createToken(findUser.getUsername(), findUser.getRoles());
    }

    public Long userUpdate(UserUpdateDto updateDto, Long id) {
        Users findUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        // 패스워드 암호화
        String securityPassword = passwordEncoder.encode(updateDto.getPassword());
        updateDto.changeSecurityPassword(securityPassword);

        return id;
    }
}
