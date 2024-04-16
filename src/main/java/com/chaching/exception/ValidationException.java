package com.chaching.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationException extends RuntimeException{

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public ValidationException() {
        this.time = LocalDateTime.now();
    }

    public ValidationException(HttpStatus status) {
        this.status = status;
    }

    public ValidationException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
