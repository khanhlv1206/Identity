package com.example.identityservice.controller;

import com.example.identityservice.IdentityServiceApplication;
import com.example.identityservice.dto.request.UserCreationRequest;
import com.example.identityservice.dto.response.UserResponse;
import com.example.identityservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")

public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private UserCreationRequest request ;
    private UserResponse userResponse;
    private LocalDate dob;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void initData(){
        dob = LocalDate.of(1990, 1,1);
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
    }


    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);
        Mockito.when(userService.createUser(ArgumentMatchers.any()))
                .thenReturn(userResponse);
        // WHEN , THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value("1001"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id")
                        .value("cf0600f538b3")
        );
    }

    @Test
    void createUser_usernameInvalid_fail() throws Exception {
        // GIVEN
        request.setUsername("inv");
        ObjectMapper mapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);

        // WHEN , THEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content)).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value("1002"))
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("Username must be least 5 character")

                );
    }
}
