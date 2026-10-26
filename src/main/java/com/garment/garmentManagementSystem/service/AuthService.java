package com.garment.garmentManagementSystem.service;

import com.garment.garmentManagementSystem.dto.LoginDto;
import com.garment.garmentManagementSystem.dto.LoginResponseDto;
import com.garment.garmentManagementSystem.dto.RegistrationDto;
import com.garment.garmentManagementSystem.dto.RegistrationResponseDto;
import com.garment.garmentManagementSystem.entity.User;
import com.garment.garmentManagementSystem.enums.Role;
import com.garment.garmentManagementSystem.exception.EmailAlreadyExistsException;
import com.garment.garmentManagementSystem.exception.MobileNumberAlreadyExistsException;
import com.garment.garmentManagementSystem.exception.UsernameAlreadyExistsException;
import com.garment.garmentManagementSystem.repository.AuthRepository;
import com.garment.garmentManagementSystem.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

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

    @Transactional
    public RegistrationResponseDto createUser(RegistrationDto registrationDto, Role role){
        if (authRepository.existsByUsername(registrationDto.getUsername())) {
            throw new UsernameAlreadyExistsException(
                    "Username already exists"
            );
        }

        if (authRepository.existsByEmail(registrationDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        if (authRepository.existsByMobileNo(registrationDto.getMobileNo())) {
            throw new MobileNumberAlreadyExistsException("Mobile number already exists");
        }

        User user = new User();

        user.setEmail(registrationDto.getEmail());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(role);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        user.setMobileNo(registrationDto.getMobileNo());
        user.setActive(true);
        User savedUser = authRepository.save(user);
        return new RegistrationResponseDto(
                savedUser.getId()
        );
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
