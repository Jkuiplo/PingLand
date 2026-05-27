package com.pingland.chat.dto.requests;

public record CreateChatRequest(
        Long currentUserId,
        Long otherUserId
) {}
