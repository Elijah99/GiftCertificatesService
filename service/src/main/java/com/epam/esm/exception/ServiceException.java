package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public abstract class ServiceException extends RuntimeException {
    private  HttpStatus status;
    private String message;
    private int errorCode;

    public abstract HttpStatus getStatus();

    @Override
    public abstract String getMessage();

    public abstract int getErrorCode();
}
