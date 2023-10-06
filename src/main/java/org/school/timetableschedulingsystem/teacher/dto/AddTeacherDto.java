package org.school.timetableschedulingsystem.teacher.dto;

import org.school.timetableschedulingsystem.models.enums.DaysOfTheWeek;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Set;

public record AddTeacherDto(
        @NonNull String firstName,
        @NonNull String lastName,
        String middleName,
        String email,
        int phoneNumber,
        String dateOfBirth,
        LocalDateTime registeredOn,
        Set<DaysOfTheWeek> availableTime,//(only for part-timers)

        String status
) {
}
