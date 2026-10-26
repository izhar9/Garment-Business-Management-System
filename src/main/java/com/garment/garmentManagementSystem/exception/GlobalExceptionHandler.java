package com.garment.garmentManagementSystem.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExists(
            UsernameAlreadyExistsException ex,
            HttpServletRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.of(
                                ex.getMessage(),
                                "USERNAME_ALREADY_EXISTS",
                                request.getRequestURI()
                        )
                );
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex,
            HttpServletRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.of(
                                ex.getMessage(),
                                "EMAIL_ALREADY_EXISTS",
                                request.getRequestURI()
                        )
                );
    }

    @ExceptionHandler(MobileNumberAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleMobileNumberAlreadyExists(
            MobileNumberAlreadyExistsException ex,
            HttpServletRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.of(
                                ex.getMessage(),
                                "MOBILE_NUMBER_ALREADY_EXISTS",
                                request.getRequestURI()
                        )
                );
    }
}