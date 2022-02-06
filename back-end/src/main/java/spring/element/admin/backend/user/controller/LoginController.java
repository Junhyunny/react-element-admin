package spring.element.admin.backend.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.element.admin.backend.user.dto.TokenDto;
import spring.element.admin.backend.user.dto.UserDto;
import spring.element.admin.backend.utils.JwtUtil;

@RestController
public class LoginController {

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserDto userDto) {
        return TokenDto.builder()
                .accessToken(JwtUtil.createAccessToken())
                .refreshToken(JwtUtil.createRefreshToken())
                .tokenType(JwtUtil.getTokenType())
                .build();
    }
}
