package com.garment.garmentManagementSystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EmployeeRole {

    STITCHING_WORKER("Stitching Worker"),
    CUTTING_MASTER("Cutting Master"),
    HELPER("Helper");

    private final String displayName;

    EmployeeRole(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static EmployeeRole fromString(String value) {
        if (value == null) {
            return null;
        }

        String input = value.trim();

        for (EmployeeRole role : values()) {
            if (role.name().equalsIgnoreCase(input)
                    || role.displayName.equalsIgnoreCase(input)) {
                return role;
            }
        }

        throw new IllegalArgumentException(
                "Invalid employee profession: " + value
        );
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}