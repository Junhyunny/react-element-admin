package spring.element.admin.backend.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.element.admin.backend.user.dto.UserDto;
import spring.element.admin.backend.user.service.UserService;

@RequiredArgsConstructor
@RestController
public class SignupController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody UserDto userDto) {
        userService.insertUserInfo(userDto);
        return "success";
    }
}
