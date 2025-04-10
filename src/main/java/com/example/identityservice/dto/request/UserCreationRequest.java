package com.example.identityservice.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.example.identityservice.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 5, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 8, message = " PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;

    @DobConstraint(min = 18, message = " INVALID_DOB")
    LocalDate dob;
}
