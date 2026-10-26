package com.garment.garmentManagementSystem.entity;

import com.garment.garmentManagementSystem.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_users_username",
                        columnNames = "username"
                ),
                @UniqueConstraint(
                        name = "uk_users_email",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "uk_users_mobile_no",
                        columnNames = "mobile_no"
                )
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            length = 50
    )
    private String username;

    @Column(
            nullable = false
    )
    private String password;

    @Column(
            nullable = false,
            length = 20
    )
    private String mobileNo;

    @Column(
            nullable = false,
            length = 255
    )
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 30
    )
    private Role role;

    @Column(
            nullable = false,
            updatable = false
    )
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @Column(
            nullable = false
    )
    private boolean active;
}