package org.school.timetableschedulingsystem.exceptions;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String message,
        String details,
        LocalDateTime timestamp
) {
}
