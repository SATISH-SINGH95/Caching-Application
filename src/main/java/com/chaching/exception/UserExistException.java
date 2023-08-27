package com.chaching.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserExistException extends RuntimeException {

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public UserExistException() {
        this.time = LocalDateTime.now();
    }

    public UserExistException(HttpStatus status) {
        this.status = status;
    }

    public UserExistException( HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
