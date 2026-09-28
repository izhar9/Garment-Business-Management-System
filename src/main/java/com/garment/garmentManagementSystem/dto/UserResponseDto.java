package com.garment.garmentManagementSystem.dto;

import com.garment.garmentManagementSystem.enums.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String mobileNo;
    private Role role;
    private LocalDateTime createdAt;
    private boolean active;
}
