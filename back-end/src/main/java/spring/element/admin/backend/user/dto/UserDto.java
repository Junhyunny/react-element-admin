package spring.element.admin.backend.user.dto;

import lombok.Builder;
import lombok.Data;
import spring.element.admin.backend.user.entity.User;

@Data
@Builder
public class UserDto {

    private String userId;
    private String password;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
