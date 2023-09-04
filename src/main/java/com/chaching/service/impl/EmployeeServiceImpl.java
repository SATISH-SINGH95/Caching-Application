package com.chaching.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.chaching.constants.EmployeeConstants;
import com.chaching.exception.EmployeeNotFoundException;
import com.chaching.model.entity.Employee;
import com.chaching.model.request.EmployeeRequestObject;
import com.chaching.model.response.EmployeeResponse;
import com.chaching.model.response.EmployeeResponseObject;
import com.chaching.repository.EmployeeRepository;
import com.chaching.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseObject createEmployee(EmployeeRequestObject employeeRequestObject) {
        log.debug("createEmployee start | employeeRequestObject = {}", employeeRequestObject);

        EmployeeResponseObject response = null;
        
        Employee employee = new Employee();
        employee.setEmployeeName(employeeRequestObject.getEmployeeName());
        employee.setEmployeeAddress(employeeRequestObject.getEmployeeAddress());
        employee.setEmployeeSalary(employeeRequestObject.getEmployeeSalary());

        Employee savedEmployee = employeeRepository.save(employee);

        if(savedEmployee != null){

            response = savedEmployee.getAsObject();

        }

        log.debug("createEmployee end | response  {}", response);
        return response;
    }

    @Override
    public EmployeeResponseObject getSingleEmployee(Long employeeId) {
        log.debug("getSingleEmployee start | employeeId = {}", employeeId);

        EmployeeResponseObject response = null;

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new EmployeeNotFoundException(HttpStatus.NOT_FOUND, EmployeeConstants.MESSAGE_EMPLOYEE_NOT_FOUND)
        );
        response = employee.getAsObject();

        log.debug("getSingleEmployee end | response  {}", response);
        return response;
    }

    @Override
    public EmployeeResponse getAllEmployee() {
        log.debug("getAllEmployee start");

        EmployeeResponse employeeResponse = new EmployeeResponse();

        List<Employee> empList = employeeRepository.findAll();
        if(empList.isEmpty()){
            throw new EmployeeNotFoundException(HttpStatus.NOT_FOUND, EmployeeConstants.MESSAGE_EMPLOYEE_LIST_EMPTY);
        };

        List<EmployeeResponseObject> responseList = empList.stream().map(emp -> emp.getAsObject()).collect(Collectors.toList());

        employeeResponse.setEmployeeList(responseList);
        log.debug("getAllEmployee end | employeeResponse  {}", employeeResponse);
        return employeeResponse;
    }

    @Override
    public EmployeeResponseObject updateEmployee(Long employeeId, EmployeeRequestObject employeeRequestObject) {
        log.debug("updateEmployee start | employeeId = {}", employeeId);

        EmployeeResponseObject response = null;

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new EmployeeNotFoundException(HttpStatus.NOT_FOUND, EmployeeConstants.MESSAGE_EMPLOYEE_NOT_FOUND)
        );

        employee.setEmployeeName(employeeRequestObject.getEmployeeName());
        employee.setEmployeeAddress(employeeRequestObject.getEmployeeAddress());
        employee.setEmployeeSalary(employeeRequestObject.getEmployeeSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        response = updatedEmployee.getAsObject();
        
        log.debug("updateEmployee end | response  {}", response);
        return response;
    }
    
}
