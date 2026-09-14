package com.garment.garmentManagementSystem.controller;

import com.garment.garmentManagementSystem.dto.LoginDto;
import com.garment.garmentManagementSystem.dto.LoginResponseDto;
import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginDto loginDto
    ) {

        LoginResponseDto response = authService.login(loginDto);

        return ResponseEntity.ok(response);
    }
}
