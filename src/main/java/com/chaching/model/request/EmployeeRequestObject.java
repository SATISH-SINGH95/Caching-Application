package com.chaching.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmployeeRequestObject {

    @Schema(description = "employeeName", example = "John")
    @NotBlank(message = "employeeName can not be Blank")
    private String employeeName;

    @Schema(description = "employeeAddress", example = "USA")
    @NotBlank(message = "employeeAddress can not be Blank")
    private String employeeAddress;

    @Schema(description = "employeeSalary", example = "10000")
    @NotNull(message = "employeeSalary can not be null")
    private Long employeeSalary;
    
}
