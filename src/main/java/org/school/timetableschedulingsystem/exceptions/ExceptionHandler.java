package org.school.timetableschedulingsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        exception.getMessage(),
                        exception.getLocalizedMessage(),
                        java.time.LocalDateTime.now()
                ),
                HttpStatus.NOT_ACCEPTABLE
        );
    }
}
