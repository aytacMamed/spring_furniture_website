package com.changeside.demo_furniture_website.services.auth;

import com.changeside.demo_furniture_website.models.dto.request.LoginRequest;
import com.changeside.demo_furniture_website.models.dto.request.RefreshTokenRequest;
import com.changeside.demo_furniture_website.models.dto.request.RegisterRequest;
import com.changeside.demo_furniture_website.models.dto.response.LoginResponse;
import com.changeside.demo_furniture_website.models.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
