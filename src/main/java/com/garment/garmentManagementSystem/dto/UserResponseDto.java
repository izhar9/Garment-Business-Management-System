package com.garment.garmentManagementSystem.dto;

import com.garment.garmentManagementSystem.enums.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String mobileNo;
    private Role role;
    private Instant createdAt;
    private boolean active;
}
