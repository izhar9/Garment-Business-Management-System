package com.garment.garmentManagementSystem.exception;

import java.time.Instant;

public record ErrorResponse(
        boolean success,
        String message,
        String errorCode,
        String path,
        Instant timestamp
) {

    public static ErrorResponse of(
            String message,
            String errorCode,
            String path
    ) {
        return new ErrorResponse(
                false,
                message,
                errorCode,
                path,
                Instant.now()
        );
    }
}
