package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class GiftCertificateNotFoundException extends ServiceException{

    private HttpStatus status = HttpStatus.NOT_FOUND;
    private String message = "Gift certificate not found";
    private int errorCode = 40400;

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
