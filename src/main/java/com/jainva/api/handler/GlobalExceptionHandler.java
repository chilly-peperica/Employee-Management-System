package com.jainva.api.handler;

import com.jainva.api.exceptions.EmployeeNotFoundException;
import com.jainva.api.exceptions.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<RestException> handleEmployeeNotFoundExceptions(Exception ex) {
        String errorMessage = "Not able to find employee due to error message. " + ex.getMessage();
        RestException re = new RestException(HttpStatus.NOT_FOUND, errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(re);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestException> handleAnyException(Exception ex) {
        String errorMessage = "Failed : " + ex.getMessage();
        RestException re = new RestException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re);
    }
}
