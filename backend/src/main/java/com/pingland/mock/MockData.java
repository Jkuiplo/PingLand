package com.pingland.mock;

import com.pingland.user.UserDto;

import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static final List<UserDto> USERS = new ArrayList<>(List.of(
            new UserDto(1L, "Hitler@gmail.com", "Hitler", "123456"),
            new UserDto(2L, "thebest@gmail.com", "Billy Herrington", "123456")
    ));
}
