package com.epam.esm.exception.jwt;

import org.springframework.http.HttpStatus;

public class CustomUnsupportedJwtException extends JwtTokenException{

    private final HttpStatus status = HttpStatus.FORBIDDEN;
    private final String message = "Unsupported jwt token";
    private final int errorCode = 40304;

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
