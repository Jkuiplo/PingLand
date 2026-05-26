package com.pingland.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignupRequest (
    String email,
    String password,
    @Schema(
            description = "For now, only nickname"
    )
    String nickname
){}
