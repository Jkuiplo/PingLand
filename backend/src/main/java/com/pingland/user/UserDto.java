package com.pingland.user;

public record UserDto (
        Long id,
        String email,
        String nickname,
        // no hash etc. for now
        String password
) {}
