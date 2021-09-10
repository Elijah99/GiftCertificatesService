package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class InvalidSearchParametersException extends ServiceException {

    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String message = "Invalid search parameters";
    private final int errorCode = 40000;

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
