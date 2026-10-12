package com.garment.garmentManagementSystem.dto;

import com.garment.garmentManagementSystem.enums.EmployeeRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeesDetailsDto {
    private Long id;

    private String name;

    private String mobileNo;

    private EmployeeRole role;

    private LocalDateTime joiningDate;

    private boolean active;
}
