package com.example.identityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED(9999, "Uncategoried Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1000, "Ivalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001, "User already exists", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Username must be least {min} character", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be at least {min} character", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1004, "User not exists", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1005, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "you do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1007, "Your age must be at least {18}", HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
