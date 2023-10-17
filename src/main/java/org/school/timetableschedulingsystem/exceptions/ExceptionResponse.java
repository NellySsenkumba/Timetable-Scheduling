package org.school.timetableschedulingsystem.exceptions;


import java.sql.Timestamp;

public record ExceptionResponse(
        String status,
        String message,
        String exception,
        Timestamp timestamp
) {
    public ExceptionResponse(String message, String exception) {
        this("-1", message, exception, new Timestamp(System.currentTimeMillis()));
    }
}
