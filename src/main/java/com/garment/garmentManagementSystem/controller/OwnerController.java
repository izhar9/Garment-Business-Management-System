package com.garment.garmentManagementSystem.controller;


import com.garment.garmentManagementSystem.dto.EmployeesDetailsDto;
import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.dto.RegistrationResponseDto;
import com.garment.garmentManagementSystem.response.ApiResponse;
import com.garment.garmentManagementSystem.service.OwnerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegistrationResponseDto>> registerOwner(
            @Valid @RequestBody RegistrationDto dto,
            HttpServletRequest request
    ) {

        ApiResponse<RegistrationResponseDto> response = ApiResponse.success(
                "Application employee registered successfully",
                null,
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/findEmployees")
    public ResponseEntity<List<EmployeesDetailsDto>> findEmployees() {
        System.out.println("Called");
        List<EmployeesDetailsDto> employees =
                ownerService.findEmployees();

        return ResponseEntity.ok(employees);
    }
}
