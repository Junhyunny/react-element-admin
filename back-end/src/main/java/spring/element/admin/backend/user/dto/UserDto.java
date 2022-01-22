package spring.element.admin.backend.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String userId;
    private String password;
}
