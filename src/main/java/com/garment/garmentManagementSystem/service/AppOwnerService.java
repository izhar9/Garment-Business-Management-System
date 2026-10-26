package com.garment.garmentManagementSystem.service;

import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.dto.RegistrationResponseDto;
import com.garment.garmentManagementSystem.enums.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppOwnerService {

    private final AuthService authService;

    public AppOwnerService(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @Transactional
    public RegistrationResponseDto registerOwner(RegistrationDto dto) {

        return authService.createUser(
                dto,
                Role.OWNER
        );
    }
}
