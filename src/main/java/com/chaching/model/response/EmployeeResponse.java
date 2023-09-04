package com.chaching.model.response;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeResponse {
    List<EmployeeResponseObject> employeeList;
}
