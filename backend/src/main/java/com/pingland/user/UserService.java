package com.pingland.user;

import com.pingland.mock.MockData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public List<UserDto> searchUsers(String username) {
        String normalizedUsername = username == null ? "" : username.toLowerCase();

        return MockData.USERS.stream()
                .filter(user -> user.username().toLowerCase().contains(normalizedUsername))
                .toList();
    }

    public UserDto getUser(Long userId) {
        return MockData.USERS.stream()
                .filter(user -> user.id().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
