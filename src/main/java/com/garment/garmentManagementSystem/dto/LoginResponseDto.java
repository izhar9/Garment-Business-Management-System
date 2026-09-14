package com.garment.garmentManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private long expiresIn;

    private String userId;
    private String username;
    private String email;
    private String role;
}
