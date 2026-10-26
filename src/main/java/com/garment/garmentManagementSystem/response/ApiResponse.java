package com.garment.garmentManagementSystem.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        String timestamp,
        String path
) {

    public static <T> ApiResponse<T> success(
            String message,
            T data,
            String path
    ) {
        return new ApiResponse<>(
                true,
                message,
                data,
                Instant.now().toString(),
                path
        );
    }

    public static <T> ApiResponse<T> failure(
            String message,
            String path
    ) {
        return new ApiResponse<>(
                false,
                message,
                null,
                Instant.now().toString(),
                path
        );
    }
}
