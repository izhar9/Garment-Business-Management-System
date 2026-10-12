package com.garment.garmentManagementSystem.service;

import com.garment.garmentManagementSystem.dto.EmployeesDetailsDto;
import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.entity.Employee;
import com.garment.garmentManagementSystem.entity.User;
import com.garment.garmentManagementSystem.enums.Role;
import com.garment.garmentManagementSystem.repository.EmployeeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final AuthService authService;

    private final EmployeeRepository employeeRepository;

    public OwnerService(
            AuthService authService,
            EmployeeRepository employeeRepository
    ) {
        this.authService = authService;
        this.employeeRepository = employeeRepository;
    }

    public String registerOwner(RegistrationDto dto) {

        return authService.createUser(
                dto,
                Role.OWNER
        );
    }

    public List<EmployeesDetailsDto> findEmployees() {
        System.out.println("findEmployees enter");
        // 1. Get the currently authenticated user.
        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();
        System.out.println("findEmployees enter 1");

        if (authentication == null ||
                !(authentication.getPrincipal() instanceof User owner)) {
            throw new RuntimeException(
                    "Authenticated user not found");
        }
        System.out.println("findEmployees enter 2");

        // 2. Only Owner can access this functionality.
        if (owner.getRole() != Role.OWNER) {
            throw new RuntimeException(
                    "Only Owner can view employees");
        }
        System.out.println("findEmployees enter 3");

        // 3. Check whether the Owner account is active.
        if (!owner.isActive()) {
            throw new RuntimeException(
                    "Owner account is inactive");
        }
        System.out.println("findEmployees enter 4");

        // 4. Fetch employees belonging to this Owner.
        List<Employee> employees =
                employeeRepository.findByOwner_Id(owner.getId());
        System.out.println("owner >> " + owner );
        System.out.println("employees >> " + employees );
        // 5. Convert Employee entities into DTOs.
        return employees.stream()
                .map(employee -> {
                    EmployeesDetailsDto dto =
                            new EmployeesDetailsDto();

                    dto.setId(employee.getId());
                    dto.setName(employee.getName());
                    dto.setMobileNo(employee.getMobileNo());
                    dto.setRole(employee.getRole());
                    dto.setJoiningDate(employee.getJoiningDate());
                    dto.setActive(employee.isActive());

                    return dto;
                })
                .toList();
    }
}