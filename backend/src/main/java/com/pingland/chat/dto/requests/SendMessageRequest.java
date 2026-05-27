package com.pingland.chat.dto.requests;

public record SendMessageRequest(
        Long senderId,
        String text
) {}
