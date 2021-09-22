package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public abstract class DaoException extends RuntimeException {

    private HttpStatus status;
    private String message;
    private int errorCode;

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
