package com.changeside.demo_furniture_website.models.dto.response;


public class RegisterResponse {
    private String message;

    public RegisterResponse(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
