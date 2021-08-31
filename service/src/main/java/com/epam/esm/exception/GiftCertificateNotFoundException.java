package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class GiftCertificateNotFoundException extends ServiceException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;
    private final String message = "Gift certificate not found";
    private final int errorCode = 40400;

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
