package spring.element.admin.backend.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spring.element.admin.backend.user.dto.UserDto;
import spring.element.admin.backend.user.service.UserService;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SignupControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    private UserDto getUserDto(String userId, String password) {
        return UserDto.builder()
                .userId(userId)
                .password(password)
                .build();
    }

    private String getUserJson(String userId, String password) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = getUserDto(userId, password);
        return objectMapper.writeValueAsString(userDto);
    }

    @Test
    public void givenRequestBody_whenSignup_thenIsOk() throws Exception {

        mockMvc.perform(
                        post("/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getUserJson("Junhyunny", "123"))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("success"))
        ;
    }

    @Test
    public void givenRequestBody_whenSignup_thenCallInsertUser() throws Exception {

        mockMvc.perform(
                post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserJson("Junhyunny", "123"))
        );

        verify(userService).insertUserInfo(getUserDto("Junhyunny", "123"));
    }
}
