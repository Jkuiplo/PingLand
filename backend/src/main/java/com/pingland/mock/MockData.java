package com.pingland.mock;

import com.pingland.chat.dto.ChatDto;
import com.pingland.chat.dto.MessageDto;
import com.pingland.user.UserDto;

import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static final List<UserDto> USERS = new ArrayList<>(List.of(
            new UserDto(1L, "john@gmail.com", "john", "John", "123456"),
            new UserDto(2L, "anna@gmail.com", "anna", "Anna", "123456"),
            new UserDto(3L, "bob@gmail.com", "bob", "Bob", "123456")
    ));

    public static final List<ChatDto> CHATS = new ArrayList<>(List.of(
            new ChatDto(1L, List.of(USERS.get(0), USERS.get(1)), null)
    ));

    public static final List<MessageDto> MESSAGES = new ArrayList<>(List.of(
            new MessageDto(1L, 1L, 1L, "Hey Anna", "2026-05-27T10:00:00"),
            new MessageDto(2L, 1L, 2L, "Hey John", "2026-05-27T10:01:00")
    ));
}
