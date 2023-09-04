package com.chaching.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chaching.model.response.EmployeeResponseObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "EMPLOYEE")
public class Employee {

    @Column(name = "EMPLOYEE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "EMPLOYEE_ADDRESS")
    private String employeeAddress;

    @Column(name = "EMPLOYEE_SALARY")
    private Long employeeSalary;


    public EmployeeResponseObject getAsObject(){
        EmployeeResponseObject object = new EmployeeResponseObject();
        object.setEmployeeName(this.employeeName);
        object.setEmployeeAddress(this.employeeAddress);
        object.setEmployeeSalary(this.employeeSalary);

        return object;
    }
}
