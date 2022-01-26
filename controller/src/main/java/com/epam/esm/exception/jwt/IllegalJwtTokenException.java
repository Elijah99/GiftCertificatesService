package com.epam.esm.exception.jwt;

import org.springframework.http.HttpStatus;

public class IllegalJwtTokenException extends JwtTokenException{

    private final HttpStatus status = HttpStatus.UNAUTHORIZED;
    private final String message = "Illegal jwt token value";
    private final int errorCode = 40305;

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
