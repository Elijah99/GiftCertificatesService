package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

public class CustomExceptionResponse {

    private final HttpStatus httpStatus;
    private final String message;
    private final int code;

    public CustomExceptionResponse(HttpStatus httpStatus, String message, int code) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
