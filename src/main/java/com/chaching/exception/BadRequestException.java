package com.chaching.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadRequestException extends RuntimeException{

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public BadRequestException() {
        this.time = LocalDateTime.now();
    }

    public BadRequestException(HttpStatus status) {
        this.status = status;
    }

    public BadRequestException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
