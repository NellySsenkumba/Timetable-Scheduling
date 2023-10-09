package org.school.timetableschedulingsystem.timeslot.dto;

import org.springframework.lang.NonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record AddTimeslotDto(
        @NonNull LocalTime startTime,
        @NonNull LocalTime endTime,
        @NonNull DayOfWeek day
) {
}
