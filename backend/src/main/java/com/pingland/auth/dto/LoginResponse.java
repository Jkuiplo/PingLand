package com.pingland.auth.dto;

import com.pingland.user.UserDto;

public record LoginResponse (
        String token,
        UserDto user
){}
