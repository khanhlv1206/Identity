package com.example.identityservice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.example.identityservice.dto.request.UserCreationRequest;
import com.example.identityservice.dto.response.UserResponse;
import com.example.identityservice.entity.User;
import com.example.identityservice.exception.AppException;
import com.example.identityservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);
        request = UserCreationRequest.builder()
                .username("admin")
                .firstName("admin")
                .lastName("admin")
                .password("admin")
                .dob(dob)
                .build();
        userResponse = UserResponse.builder()
                .id("cf0600f538b3")
                .username("admin")
                .firstName("admin")
                .lastName("admin")
                .dob(dob)
                .build();
        user = User.builder()
                .id("cf0600f538b3")
                .username("admin")
                .firstName("admin")
                .lastName("admin")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("cf0600f538b3");
        Assertions.assertThat(response.getUsername()).isEqualTo("admin");
    }

    @Test
    void createUser_userExist_fail() throws Exception {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        // THEN
        Assertions.assertThat(exception.getMessage()).isEqualTo("1001");
    }

    @Test
    @WithMockUser(username = "admin")
    void getMyInfo_valid_success() throws Exception {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        var response = userService.getMyInfo();
        Assertions.assertThat(response.getUsername()).isEqualTo("admin");
        Assertions.assertThat(response.getUsername()).isEqualTo("cf0600f538b3");
    }

    @Test
    @WithMockUser(username = "admin")
    void getMyInfo_userNotFound_error() throws Exception {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo("1004");
    }
}
