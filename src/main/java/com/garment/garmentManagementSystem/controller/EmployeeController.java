package com.garment.garmentManagementSystem.controller;

import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<String> registerEmployee(
            @Valid @RequestBody RegistrationDto dto
    ) {

        String message = employeeService.registerEmployee(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }
}
