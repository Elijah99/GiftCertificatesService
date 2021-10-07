package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends DaoException {
    private final HttpStatus status = HttpStatus.NOT_FOUND;
    private final String message = "User not found";
    private final int errorCode = 40402;

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
