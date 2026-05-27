package com.pingland.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record UserDto (
        Long id,
        String email,
        String username,
        String nickname,
        // no hash etc. for now
        @JsonIgnore
        String password
) {}
