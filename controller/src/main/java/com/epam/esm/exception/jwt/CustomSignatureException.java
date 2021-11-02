package com.epam.esm.exception.jwt;

import org.springframework.http.HttpStatus;

public class CustomSignatureException extends JwtTokenException {

    private final HttpStatus status = HttpStatus.FORBIDDEN;
    private final String message = "Wrong jwt token signature";
    private final int errorCode = 40301;

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
