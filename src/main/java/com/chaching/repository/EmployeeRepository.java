package com.chaching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.chaching.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    @Procedure(value = "getAllEmployeeProcedure")
    public List<Employee> getEmployees();
}
