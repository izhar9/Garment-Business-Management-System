package com.garment.garmentManagementSystem.controller;


import com.garment.garmentManagementSystem.dto.EmployeesDetailsDto;
import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.service.OwnerService;
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
    public String registerOwner(
            @Valid @RequestBody RegistrationDto dto
    ) {

        String message = ownerService.registerOwner(dto);

        return message;
    }

    @GetMapping("/findEmployees")
    public ResponseEntity<List<EmployeesDetailsDto>> findEmployees() {
        System.out.println("Called");
        List<EmployeesDetailsDto> employees =
                ownerService.findEmployees();

        return ResponseEntity.ok(employees);
    }
}
