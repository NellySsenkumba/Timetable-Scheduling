package org.school.timetableschedulingsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> wrongCredentials() {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        "Bad Credentials",
                        "Wrong username or password"
                ),
                HttpStatus.UNAUTHORIZED
        );

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        "General Exception",
                        exception.getLocalizedMessage() + ((exception.getCause() == null) ? "" : exception.getCause())

                ),
                HttpStatus.NOT_ACCEPTABLE
        );
    }
}
