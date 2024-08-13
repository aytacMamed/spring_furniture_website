package com.changeside.demo_furniture_website.controller;

import com.changeside.demo_furniture_website.models.dto.request.LoginRequest;
import com.changeside.demo_furniture_website.models.dto.request.RefreshTokenRequest;
import com.changeside.demo_furniture_website.models.dto.request.RegisterRequest;
import com.changeside.demo_furniture_website.models.dto.response.LoginResponse;
import com.changeside.demo_furniture_website.models.dto.response.RegisterResponse;
import com.changeside.demo_furniture_website.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/v1")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequestDto) {
        return authService.login(loginRequestDto);
    }


    @PostMapping("/refresh")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
}
