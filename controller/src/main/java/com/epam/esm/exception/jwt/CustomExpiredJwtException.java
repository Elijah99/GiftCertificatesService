package com.epam.esm.exception.jwt;

import org.springframework.http.HttpStatus;

public class CustomExpiredJwtException extends JwtTokenException {

    private final HttpStatus status = HttpStatus.FORBIDDEN;
    private final String message = "Jwt token expired";
    private final int errorCode = 40303;

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
