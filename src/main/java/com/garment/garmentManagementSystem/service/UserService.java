package com.garment.garmentManagementSystem.service;

import com.garment.garmentManagementSystem.dto.UserResponseDto;
import com.garment.garmentManagementSystem.entity.User;
import com.garment.garmentManagementSystem.enums.Role;
import com.garment.garmentManagementSystem.repository.AuthRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final AuthRepository authRepository;

    public UserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public ResponseEntity<?> getUsersByRole(Role role) {
        // Get all owners
        List<User> users = authRepository.findByRoleOrderByIdAsc(role);

        // Convert User -> DTO
        List<UserResponseDto> response = users.stream()
                .map(user -> {
                    UserResponseDto dto = new UserResponseDto();

                    dto.setId(user.getId());
                    dto.setUsername(user.getUsername());
                    dto.setEmail(user.getEmail());
                    dto.setMobileNo(user.getMobileNo());
                    dto.setRole(user.getRole());
                    dto.setCreatedAt(user.getCreatedAt());
                    dto.setActive(user.isActive());

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(response);
    }
}