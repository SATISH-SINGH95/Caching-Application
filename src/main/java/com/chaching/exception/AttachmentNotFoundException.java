package com.chaching.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttachmentNotFoundException extends RuntimeException{

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public AttachmentNotFoundException() {
        this.time = LocalDateTime.now();
    }

    public AttachmentNotFoundException(HttpStatus status) {
        this.status = status;
    }

    public AttachmentNotFoundException( HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    
}
