package com.pingland.chat;

import com.pingland.chat.dto.ChatDto;
import com.pingland.chat.dto.MessageDto;
import com.pingland.chat.dto.requests.CreateChatRequest;
import com.pingland.chat.dto.requests.SendMessageRequest;
import com.pingland.chat.dto.responses.CreateChatResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public CreateChatResponse createOrGetChat(@RequestBody CreateChatRequest request) {
        return chatService.createOrGetChat(request);
    }

    @GetMapping
    public List<ChatDto> getChats(@RequestParam Long userId) {
        return chatService.getChats(userId);
    }

    @GetMapping("/{chatId}/messages")
    public List<MessageDto> getMessages(@PathVariable Long chatId) {
        return chatService.getMessages(chatId);
    }

    @PostMapping("/{chatId}/messages")
    public MessageDto sendMessage(@PathVariable Long chatId, @RequestBody SendMessageRequest request) {
        return chatService.sendMessage(chatId, request);
    }
}
