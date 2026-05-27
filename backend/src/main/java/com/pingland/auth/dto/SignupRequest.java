package com.pingland.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignupRequest (
    String email,
    @Schema(
            description = "Unique username used for searching users across the app.",
            example = "john"
    )
    String username,
    String password,
    @Schema(
            description = "Non-unique public display name. User can choose any nickname.",
            example = "John"
    )
    String nickname
){}
