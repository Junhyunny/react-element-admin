package spring.element.admin.backend.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.element.admin.backend.user.dto.TokenDto;
import spring.element.admin.backend.user.dto.UserDto;
import spring.element.admin.backend.user.service.UserService;
import spring.element.admin.backend.utils.JwtUtil;

import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserDto userDto) {
        UserDetails userDetails = userService.loadUserByUsername(userDto.getUserId());
        if (!createDelegatingPasswordEncoder().matches(userDto.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("password is not matched");
        }
        return TokenDto.builder()
                .accessToken(JwtUtil.createAccessToken())
                .refreshToken(JwtUtil.createRefreshToken())
                .tokenType(JwtUtil.getTokenType())
                .build();
    }
}
