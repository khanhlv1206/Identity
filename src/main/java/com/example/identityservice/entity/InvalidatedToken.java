package com.example.identityservice.entity;

import java.util.Date;

import jakarta.persistence.Id;

import lombok.Builder;

@Builder
public class InvalidatedToken {
    @Id
    String id;

    Date expiryTime;
}
