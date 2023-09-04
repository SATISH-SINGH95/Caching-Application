package com.chaching.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaching.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
