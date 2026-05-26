package com.pingland.auth;

import com.pingland.auth.dto.LoginRequest;
import com.pingland.auth.dto.LoginResponse;
import com.pingland.auth.dto.SignupRequest;
import com.pingland.auth.dto.SignupResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/signup")
    public SignupResponse signupResponse(@RequestBody SignupRequest request){
        return authService.signup(request);
    }
}
