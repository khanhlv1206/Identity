package com.example.identityservice.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
public class InvalidatedToken {
    @Id
    String id;
    Date expiryTime;
}
