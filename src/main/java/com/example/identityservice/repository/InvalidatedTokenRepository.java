package com.example.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.identityservice.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
