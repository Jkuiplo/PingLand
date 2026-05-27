package com.pingland.chat.dto.responses;

import com.pingland.chat.dto.ChatDto;

public record CreateChatResponse(
        boolean created,
        ChatDto chat
) {}
