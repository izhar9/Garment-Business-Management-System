package com.garment.garmentManagementSystem.repository;

import com.garment.garmentManagementSystem.dto.EmployeesDetailsDto;
import com.garment.garmentManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByOwner_Id(Long ownerId);
}
