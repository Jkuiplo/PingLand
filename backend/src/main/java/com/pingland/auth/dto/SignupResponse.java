package com.pingland.auth.dto;

import com.pingland.user.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignupResponse (
        String token,
        @Schema(
                description = "Alyaska gay"
        )
        UserDto user
) {}
