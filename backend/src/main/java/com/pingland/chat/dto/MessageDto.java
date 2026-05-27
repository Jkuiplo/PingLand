package com.pingland.chat.dto;

import java.util.List;

public record MessageDto(
        Long id,
        Long chatId,
        Long senderId,
        String text,
        String createdAt
) {}
