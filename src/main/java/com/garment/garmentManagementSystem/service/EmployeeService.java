package com.garment.garmentManagementSystem.service;

import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.entity.Employee;
import com.garment.garmentManagementSystem.entity.User;
import com.garment.garmentManagementSystem.enums.Role;
import com.garment.garmentManagementSystem.repository.AuthRepository;
import com.garment.garmentManagementSystem.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class EmployeeService {
    private final AuthRepository authRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(
            AuthRepository authRepository,
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authRepository = authRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public String registerEmployee(RegistrationDto dto) {

        // 1. Get the currently authenticated user.
        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        if (authentication == null ||
                !(authentication.getPrincipal() instanceof User owner)) {
            throw new RuntimeException(
                    "Authenticated user not found");
        }

        // 2. Verify that the authenticated user is an Owner.
        if (owner.getRole() != Role.OWNER) {
            throw new RuntimeException(
                    "Only an Owner can register employees");
        }

        // 3. Check whether the Owner account is active.
        if (!owner.isActive()) {
            throw new RuntimeException(
                    "Owner account is inactive");
        }

        // 4. Check duplicate username.
        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // 5. Check duplicate email.
        if (authRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // 6. Check duplicate mobile number.
        if (authRepository.existsByMobileNo(dto.getMobileNo())) {
            throw new RuntimeException("Mobile number already exists");
        }

        LocalDateTime now = LocalDateTime.now();

        // 7. Create the employee's login account.
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        user.setEmail(dto.getEmail());
        user.setMobileNo(dto.getMobileNo());
        user.setRole(Role.EMPLOYEE);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        user.setActive(true);

        User savedUser = authRepository.save(user);

        // 8. Create the employee profile.
        Employee employee = new Employee();

        employee.setUser(savedUser);
        employee.setOwner(owner);
        employee.setName(dto.getName());
        employee.setMobileNo(dto.getMobileNo());
        employee.setRole(dto.getProfession());
        employee.setJoiningDate(now);
        employee.setActive(true);

        employeeRepository.save(employee);

        return "Employee registered successfully";
    }
}
