package com.jainva.api.handler;

import com.jainva.api.exceptions.EMSException;
import com.jainva.api.exceptions.EmployeeNotFoundException;
import com.jainva.api.exceptions.RestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<RestException> handleEmployeeNotFoundExceptions(Exception ex) {
        log.error("Exception for EmployeeNotFoundException detected in controller laye via global handlers :", ex.getStackTrace() );
        String errorMessage = "Not able to find employee due to error message. " + ex.getMessage();
        RestException re = new RestException(HttpStatus.NOT_FOUND, errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(re);
    }


    @ExceptionHandler(EMSException.class)
    public ResponseEntity<RestException> handleEMSExceptions(Exception ex) {
        log.error("Exception EMSException detected in controllers via global hanlders", ex.getStackTrace());
        String errorMessage = "Internal Server Error: " + ex.getMessage();
        RestException re = new RestException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<RestException> hanndleExceptionsComingFromJDBC(Exception ex) {
        log.error("Exception detected in JDBC layer", ex.getStackTrace() );
        String errorMessage = "Failed to access DB layer due to : " + ex.getMessage();
        RestException re = new RestException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestException> handleCheckedExceptions(Exception ex) {
        log.error("Exception handler for Exception exception, UNHANDLED EXCEPTION DETECTED P0", ex.getStackTrace());
        String errorMessage = "Unhandled Checked Exception found : " + ex.getMessage();
        RestException re = new RestException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestException> handleUncheckedExceptions(Exception ex) {
        log.error("Exception handler for Exception exception, UNHANDLED EXCEPTION DETECTED P0", ex.getStackTrace());
        String errorMessage = "Unhandled Un-Checked Runtime Exception Found : " + ex.getLocalizedMessage();
        RestException re = new RestException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re);
    }


}
