package spring.element.admin.backend.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring.element.admin.backend.user.dto.UserDto;

public interface UserService extends UserDetailsService {

    void insertUserInfo(UserDto userDto);
}
