package com.jainva.api.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class RestException {
    private final HttpStatus status;
    private final String message;

    public Date getExceptionTime() {
        return exceptionTime;
    }

    public int getStatus_code() {
        return status_code;
    }

    private final Date exceptionTime;
    private final int status_code;

    public RestException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.status_code = status.value();
        this.exceptionTime = new Date();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
