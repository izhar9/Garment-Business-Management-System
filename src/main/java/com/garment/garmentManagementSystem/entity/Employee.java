package com.garment.garmentManagementSystem.entity;

import com.garment.garmentManagementSystem.enums.EmployeeRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    // Many employees can belong to one owner.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    private String mobileNo;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    private LocalDateTime joiningDate;

    private boolean active;

}
