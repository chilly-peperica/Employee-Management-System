package com.jainva.api.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message) {

        super(message);
    }

    public EmployeeNotFoundException(String msg, Throwable err) {
        super(msg, err);
    }
}
