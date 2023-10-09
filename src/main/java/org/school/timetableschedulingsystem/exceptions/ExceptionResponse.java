package org.school.timetableschedulingsystem.exceptions;


import java.sql.Timestamp;

public record ExceptionResponse(
        String status,
        String message,
        String exception,
        Timestamp timestamp
) {
    public ExceptionResponse(String status, String message) {
        this("-1", status, message, new Timestamp(System.currentTimeMillis()));
    }
}
