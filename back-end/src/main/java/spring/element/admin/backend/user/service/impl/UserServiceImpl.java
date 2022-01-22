package spring.element.admin.backend.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.element.admin.backend.user.dto.UserDto;
import spring.element.admin.backend.user.repository.UserRepository;
import spring.element.admin.backend.user.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public void insertUserInfo(UserDto userDto) {
        repository.save(userDto.toEntity());
    }
}
