package com.epam.esm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomExceptionResponse> handleServiceExceptions(AccessDeniedException ex) {
        CustomAccessDeniedException customException = new CustomAccessDeniedException();
        CustomExceptionResponse response = new CustomExceptionResponse(customException.getStatus(),
                customException.getMessage(),
                customException.getErrorCode());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<CustomExceptionResponse> handleServiceExceptions(ControllerException ex) {
        CustomExceptionResponse response = new CustomExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<CustomExceptionResponse> handleServiceExceptions(ServiceException ex) {
        CustomExceptionResponse response = new CustomExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @ExceptionHandler(DaoException.class)
    public ResponseEntity<CustomExceptionResponse> handleDaoExceptions(DaoException ex) {
        CustomExceptionResponse response = new CustomExceptionResponse(ex.getStatus(), ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionResponse> handleProjectException(Exception ex) {
        CustomExceptionResponse response = new CustomExceptionResponse(HttpStatus.BAD_REQUEST, "Bad request", 40011);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
