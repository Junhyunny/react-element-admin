package spring.element.admin.backend.user.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spring.element.admin.backend.user.dto.UserDto;
import spring.element.admin.backend.user.repository.UserRepository;
import spring.element.admin.backend.user.service.impl.UserServiceImpl;

import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Test
    public void givenUserDto_whenInsertUserInfo_thenCallSave() {

        UserDto userDto = UserDto.builder()
                .userId("Junhyunny")
                .password("123")
                .build();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepository);

        userService.insertUserInfo(userDto);

        verify(userRepository).save(userDto.toEntity());
    }
}
