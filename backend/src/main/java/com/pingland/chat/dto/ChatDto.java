package com.pingland.chat.dto;

import com.pingland.user.UserDto;

import java.util.List;

public record ChatDto(
        Long id,
        List<UserDto> participants,
        MessageDto lastMessage
) {}
