package com.garment.garmentManagementSystem.controller;


import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.dto.RegistrationResponseDto;
import com.garment.garmentManagementSystem.response.ApiResponse;
import com.garment.garmentManagementSystem.service.AppOwnerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app-owner")
public class AppOwnerController {
    private final AppOwnerService appOwnerService;

    public AppOwnerController(AppOwnerService appOwnerService) {
        this.appOwnerService = appOwnerService;
    }

    @PostMapping("/registerOwner")
    public ResponseEntity<ApiResponse<RegistrationResponseDto>> registerOwner(
            @Valid @RequestBody RegistrationDto dto,
            HttpServletRequest request
    ) {

        RegistrationResponseDto result = appOwnerService.registerOwner(dto);

        ApiResponse<RegistrationResponseDto> response = ApiResponse.success(
                "Application owner registered successfully",
                null,
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
