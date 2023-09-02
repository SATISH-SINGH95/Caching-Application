package com.chaching.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MethodArgumentError {

    private LocalDateTime time;
    private HttpStatus status;
    private Map<String, String> errorMap;

    public MethodArgumentError() {
        this.time = LocalDateTime.now();
    }
    
    public MethodArgumentError(HttpStatus status) {
        this.status = status;
    }

    public MethodArgumentError(HttpStatus status, LocalDateTime time) {
        this.status = status;
        this.time =time;
    }

    public MethodArgumentError(HttpStatus status, Map<String, String> errorMap, LocalDateTime time ) {
        this.status = status;
        this.errorMap = errorMap;
        this.time =time;
        
    }

    
}
