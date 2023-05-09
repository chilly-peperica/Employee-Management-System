package com.jainva.api.exceptions;

public class EMSException extends RuntimeException {

    public EMSException(String message) {
        super(message);
    }

    public EMSException(String msg, Throwable err) {
        super(msg, err);
    }
}
