package com.chaching.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public ErrorDetails() {
        this.time = LocalDateTime.now();
    }
    
    public ErrorDetails(HttpStatus status) {
        this.status = status;
    }

    public ErrorDetails(HttpStatus status, String message, LocalDateTime time) {
        this.message = message;
        this.status = status;
        this.time =time;
    }

}
