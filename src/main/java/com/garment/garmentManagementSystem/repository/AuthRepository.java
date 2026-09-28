package com.garment.garmentManagementSystem.repository;

import com.garment.garmentManagementSystem.entity.User;
import com.garment.garmentManagementSystem.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByRoleOrderByIdAsc(Role role);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByMobileNo(String mobileNo);

}