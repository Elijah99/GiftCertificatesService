package com.epam.esm.exception.jwt;

import org.springframework.http.HttpStatus;

public class CustomMalformedJwtException extends JwtTokenException{

    private final HttpStatus status = HttpStatus.FORBIDDEN;
    private final String message = "Jwt token was not correctly constructed";
    private final int errorCode = 40302;

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
