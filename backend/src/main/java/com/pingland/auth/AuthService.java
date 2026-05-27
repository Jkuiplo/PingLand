package com.pingland.auth;

import com.pingland.auth.dto.LoginRequest;
import com.pingland.auth.dto.LoginResponse;
import com.pingland.auth.dto.SignupRequest;
import com.pingland.auth.dto.SignupResponse;
import com.pingland.mock.MockData;
import com.pingland.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public LoginResponse login(LoginRequest request) {
        UserDto user = MockData.USERS.stream()
                .filter(u -> u.email().equals(request.email()) && u.password().equals(request.password()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User is invalid, or password is incorrect"));
        return new LoginResponse("fake-token-" + user.id(), user);
    }

    public SignupResponse signup(SignupRequest request){
        if (MockData.USERS.stream().anyMatch(u -> u.email().equals(request.email()))) {
            throw new RuntimeException("Email is already taken");
        }
        if (MockData.USERS.stream().anyMatch(u -> u.username().equals(request.username()))) {
            throw new RuntimeException("Username is already taken");
        }

        UserDto user = new UserDto(nextUserId(), request.email(), request.username(), request.nickname(), request.password());
        MockData.USERS.add(user);
        return new SignupResponse("fake-token-" + user.id(), user);
    }

    private Long nextUserId() {
        return MockData.USERS.stream()
                .mapToLong(UserDto::id)
                .max()
                .orElse(0L) + 1;
    }
}
