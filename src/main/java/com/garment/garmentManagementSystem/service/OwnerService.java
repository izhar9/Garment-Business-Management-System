package com.garment.garmentManagementSystem.service;

import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final AuthService authService;

    public OwnerService(AuthService authService) {
        this.authService = authService;
    }

    public String registerOwner(RegistrationDto dto) {

        return authService.createUser(
                dto,
                Role.OWNER
        );
    }
}