package com.garment.garmentManagementSystem.service;

import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final AuthService authService;

    public EmployeeService(AuthService authService) {
        this.authService = authService;
    }

    public String registerEmployee(RegistrationDto dto) {

        return authService.createUser(
                dto,
                Role.EMPLOYEE
        );
    }
}
