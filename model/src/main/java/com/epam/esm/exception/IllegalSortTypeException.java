package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class IllegalSortTypeException extends DaoException {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String message = "Illegal sort type";
    private final int errorCode = 40413;

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
