package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends ServiceException {
    private final HttpStatus status = HttpStatus.NOT_FOUND;
    private final String message = "Order not found";
    private final int errorCode = 40403;

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
