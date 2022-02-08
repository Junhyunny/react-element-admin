package spring.element.admin.backend.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.element.admin.backend.user.dto.TokenDto;
import spring.element.admin.backend.user.dto.UserDto;
import spring.element.admin.backend.user.service.UserService;
import spring.element.admin.backend.utils.JwtUtil;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerTest {

    MockMvc mockMvc;

    UserService mockUserService;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockUserService = Mockito.mock(UserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(mockUserService)).build();
    }

    @Test
    public void whenLogin_thenIs4xxClientError() throws Exception {

        mockMvc.perform(post("/login"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenUserDto_whenLogin_thenReturnAccessToken() throws Exception {

        UserDto userDto = UserDto.builder()
                .userId("Junhyunny")
                .password("1234")
                .build();
        MockedStatic<JwtUtil> utilities = Mockito.mockStatic(JwtUtil.class);
        utilities.when(JwtUtil::createAccessToken).thenReturn("accessToken");
        utilities.when(JwtUtil::createRefreshToken).thenReturn("refreshToken");
        utilities.when(JwtUtil::getTokenType).thenReturn("Bearer");

        when(mockUserService.loadUserByUsername("Junhyunny")).thenReturn(
                User.builder()
                        .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                        .username("Junhyunny")
                        .password("1234")
                        .authorities("ADMIN")
                        .build()
        );

        MvcResult mvcResult = mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isOk())
                .andReturn();


        TokenDto tokenDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TokenDto.class);
        assertThat(tokenDto.getAccessToken(), equalTo("accessToken"));
        assertThat(tokenDto.getRefreshToken(), equalTo("refreshToken"));
        assertThat(tokenDto.getTokenType(), equalTo("Bearer"));
    }

    @Test
    public void givenNotExistsUser_whenLogin_thenThrowUserNameNotFoundException() throws Exception {

        UserDto userDto = UserDto.builder()
                .userId("Junhyunny")
                .password("1234")
                .build();

        when(mockUserService.loadUserByUsername("Junhyunny")).thenThrow(new UsernameNotFoundException("Not found user by username"));

        assertThatThrownBy(() ->
                mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(userDto))
                )
        ).hasCause(new UsernameNotFoundException("Not found user by username"));

    }

    @Test
    public void givenNotMatchedPassword_whenLogin_thenThrowRuntimeException() throws Exception {

        UserDto userDto = UserDto.builder()
                .userId("Junhyunny")
                .password("1234")
                .build();

        when(mockUserService.loadUserByUsername("Junhyunny")).thenReturn(
                User.builder()
                        .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                        .username("Junhyunny")
                        .password("12345")
                        .authorities("ADMIN")
                        .build()
        );

        assertThatThrownBy(() ->
                mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(userDto))
                )
        ).hasCause(new RuntimeException("password is not matched"));
    }
}
