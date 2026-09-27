package com.garment.garmentManagementSystem.service;

import com.garment.garmentManagementSystem.dto.LoginDto;
import com.garment.garmentManagementSystem.dto.LoginResponseDto;
import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.entity.User;
import com.garment.garmentManagementSystem.enums.Role;
import com.garment.garmentManagementSystem.repository.AuthRepository;
import com.garment.garmentManagementSystem.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthService(AuthRepository authRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService

    ){
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String createUser(RegistrationDto registrationDto, Role role){
        if (authRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (authRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (authRepository.existsByMobileNo(registrationDto.getMobileNo())) {
            throw new RuntimeException("Mobile number already exists");
        }

        User user = new User();

        LocalDateTime now = LocalDateTime.now();

        user.setEmail(registrationDto.getEmail());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(role);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setMobileNo(registrationDto.getMobileNo());
        user.setActive(true);
        authRepository.save(user);
        return "Registration successfully";
    }

    public LoginResponseDto login(LoginDto dto) {

        User user = authRepository
                .findByUsername(dto.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password")
                );

//        if (!user.isActive()) {
//            throw new RuntimeException("User is inactive");
//        }

        if (!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid username or password");
        }

        String accessToken =
                jwtService.generateAccessToken(user);

        String refreshToken =
                jwtService.generateRefreshToken(user);


        return new LoginResponseDto(
                accessToken,
                refreshToken,
                900,
                user.getId().toString(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
