package org.school.timetableschedulingsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleWrongCredentials() {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        "Bad Credentials",
                        "Wrong username or password"
                ),
                HttpStatus.UNAUTHORIZED
        );

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        "HttpMessage Empty",
                        "Request body is required for this request"

                ),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        "General Exception: " + exception.getClass(),
                        exception.getLocalizedMessage() + "\n\t" + ((exception.getCause() == null) ? "" : exception.getCause())

                ),
                HttpStatus.NOT_ACCEPTABLE
        );
    }
}
