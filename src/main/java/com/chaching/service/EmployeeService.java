package com.chaching.service;

import com.chaching.model.request.EmployeeRequestObject;
import com.chaching.model.response.EmployeeResponse;
import com.chaching.model.response.EmployeeResponseObject;

public interface EmployeeService {

    EmployeeResponseObject createEmployee(EmployeeRequestObject employeeRequestObject);

    EmployeeResponseObject getSingleEmployee(Long employeeeId);

    EmployeeResponse getAllEmployee();

    EmployeeResponseObject updateEmployee(Long employeeId, EmployeeRequestObject employeeRequestObject);
    
}
