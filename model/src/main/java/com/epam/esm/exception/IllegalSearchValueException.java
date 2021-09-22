package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class IllegalSearchValueException extends DaoException {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String message = "Illegal search value or parameter";
    private final int errorCode = 40414;

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
