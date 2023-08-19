package com.chaching.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException{

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public EntityNotFoundException() {
        this.time = LocalDateTime.now();
    }

    public EntityNotFoundException(HttpStatus status) {
        this.status = status;
    }

    public EntityNotFoundException( HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    
}
