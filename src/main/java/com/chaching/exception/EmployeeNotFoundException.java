package com.chaching.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeNotFoundException extends RuntimeException{

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public EmployeeNotFoundException() {
        this.time = LocalDateTime.now();
    }

    public EmployeeNotFoundException(HttpStatus status) {
        this.status = status;
    }

    public EmployeeNotFoundException( HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
