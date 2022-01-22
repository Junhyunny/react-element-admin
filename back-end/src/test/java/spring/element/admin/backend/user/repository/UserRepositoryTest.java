package spring.element.admin.backend.user.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.element.admin.backend.user.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.show-sql=true"
        }
)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSave_thenFindUserInfo() {

        User user = User.builder()
                .userId("Junhyunny")
                .password("123")
                .build();

        userRepository.save(user);

        User foundUser = userRepository.findById("Junhyunny").orElse(null);
        assertThat(foundUser).isNotNull();
    }
}
