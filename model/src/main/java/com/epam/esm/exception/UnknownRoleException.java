package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class UnknownRoleException extends DaoException {
    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private final String message = "Unknown role of user";
    private final int errorCode = 50002;

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
