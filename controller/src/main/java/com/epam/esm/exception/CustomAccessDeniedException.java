package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class CustomAccessDeniedException extends ControllerException {
    private final HttpStatus status = HttpStatus.FORBIDDEN;
    private final String message = "Access denied";
    private final int errorCode = 40300;

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
