package com.chaching.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.Data;

@RestControllerAdvice
@Data
public class GlobalExceptionHandler{

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> notFoundException(EntityNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getStatus(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public ResponseEntity<Object> handleMethodArgumentNoSupportedException(MethodArgumentNotValidException ex, WebRequest request){
    //     List<String> errorMessages = new ArrayList<>();
    //     for(ObjectError error : ex.getBindingResult().getAllErrors()){
    //         errorMessages.add(error.getDefaultMessage());
    //     }

    //     ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, errorMessages.toString(), LocalDateTime.now());
    //     return new ResponseEntity<Object>(errorDetails,errorDetails.getStatus());
    // }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNoSupportedException(MethodArgumentNotValidException ex, WebRequest request){
       Map<String, String> errorMap = new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach(error -> {
        errorMap.put(error.getField(), error.getDefaultMessage());
       });

       MethodArgumentError error = new MethodArgumentError(HttpStatus.BAD_REQUEST, errorMap, LocalDateTime.now());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestExceptionMethod(BadRequestException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getStatus(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
    }

    @ExceptionHandler(AttachmentNotFoundException.class)
    public ResponseEntity<Object> attachmentNotFoundExceptionMethod(AttachmentNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getStatus(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());

    }

    @ExceptionHandler(InvalidFileNameException.class)
    public ResponseEntity<Object> invalidFileNameExceptionMethod(InvalidFileNameException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getStatus(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getStatus(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFound(EmployeeNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getStatus(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationException(ValidationException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getStatus(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
    }

}
