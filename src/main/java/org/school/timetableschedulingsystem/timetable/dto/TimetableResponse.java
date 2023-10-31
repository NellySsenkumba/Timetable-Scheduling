package org.school.timetableschedulingsystem.timetable.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.DayOfWeek;

public record TimetableResponse(
        String teacher,
        String subject,
        String stream,
        @Enumerated(EnumType.STRING)
        DayOfWeek day,
        String startTime,
        String endTime

        ) {
}
