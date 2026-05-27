package com.pingland.chat;

import com.pingland.chat.dto.ChatDto;
import com.pingland.chat.dto.MessageDto;
import com.pingland.chat.dto.requests.CreateChatRequest;
import com.pingland.chat.dto.requests.SendMessageRequest;
import com.pingland.chat.dto.responses.CreateChatResponse;
import com.pingland.mock.MockData;
import com.pingland.user.UserDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class ChatService {
    public CreateChatResponse createOrGetChat(CreateChatRequest request) {
        if (request.currentUserId().equals(request.otherUserId())) {
            throw new RuntimeException("You cannot create chat with yourself");
        }

        UserDto currentUser = findUser(request.currentUserId());
        UserDto otherUser = findUser(request.otherUserId());

        return MockData.CHATS.stream()
                .filter(chat -> hasParticipant(chat, currentUser.id()) && hasParticipant(chat, otherUser.id()))
                .findFirst()
                .map(chat -> new CreateChatResponse(false, withLastMessage(chat)))
                .orElseGet(() -> {
                    ChatDto chat = new ChatDto(nextChatId(), List.of(currentUser, otherUser), null);
                    MockData.CHATS.add(chat);
                    return new CreateChatResponse(true, chat);
                });
    }

    public List<ChatDto> getChats(Long userId) {
        findUser(userId);

        return MockData.CHATS.stream()
                .filter(chat -> hasParticipant(chat, userId))
                .map(this::withLastMessage)
                .toList();
    }

    public List<MessageDto> getMessages(Long chatId) {
        findChat(chatId);

        return MockData.MESSAGES.stream()
                .filter(message -> message.chatId().equals(chatId))
                .toList();
    }

    public MessageDto sendMessage(Long chatId, SendMessageRequest request) {
        ChatDto chat = findChat(chatId);

        if (!hasParticipant(chat, request.senderId())) {
            throw new RuntimeException("Sender is not participant of this chat");
        }

        MessageDto message = new MessageDto(
                nextMessageId(),
                chatId,
                request.senderId(),
                request.text(),
                LocalDateTime.now().toString()
        );

        MockData.MESSAGES.add(message);
        return message;
    }

    private UserDto findUser(Long userId) {
        return MockData.USERS.stream()
                .filter(user -> user.id().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private ChatDto findChat(Long chatId) {
        return MockData.CHATS.stream()
                .filter(chat -> chat.id().equals(chatId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Chat not found"));
    }

    private boolean hasParticipant(ChatDto chat, Long userId) {
        return chat.participants().stream()
                .anyMatch(user -> user.id().equals(userId));
    }

    private ChatDto withLastMessage(ChatDto chat) {
        MessageDto lastMessage = MockData.MESSAGES.stream()
                .filter(message -> message.chatId().equals(chat.id()))
                .max(Comparator.comparing(MessageDto::id))
                .orElse(null);

        return new ChatDto(chat.id(), chat.participants(), lastMessage);
    }

    private Long nextChatId() {
        return MockData.CHATS.stream()
                .mapToLong(ChatDto::id)
                .max()
                .orElse(0L) + 1;
    }

    private Long nextMessageId() {
        return MockData.MESSAGES.stream()
                .mapToLong(MessageDto::id)
                .max()
                .orElse(0L) + 1;
    }
}
