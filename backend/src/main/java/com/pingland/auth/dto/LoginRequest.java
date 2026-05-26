package com.pingland.auth.dto;

public record LoginRequest (
        String email,
        String password
) {}
