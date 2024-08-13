package com.changeside.demo_furniture_website.models.dto.response;


public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}

