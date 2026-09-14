package com.garment.garmentManagementSystem.controller;


import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<String> registerOwner(
            @Valid @RequestBody RegistrationDto dto
    ) {

        String message = ownerService.registerOwner(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }
}
