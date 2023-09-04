package com.chaching.service;

import java.util.List;

import com.chaching.model.request.EmployeeRequestObject;
import com.chaching.model.response.EmployeeResponseObject;

public interface EmployeeService {

    EmployeeResponseObject createEmployee(EmployeeRequestObject employeeRequestObject);

    EmployeeResponseObject getSingleEmployee(Long employeeeId);

    List<EmployeeResponseObject> getAllEmployee();

    EmployeeResponseObject updateEmployee(Long employeeId, EmployeeRequestObject employeeRequestObject);
    
}
