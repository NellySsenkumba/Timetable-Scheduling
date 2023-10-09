package org.school.timetableschedulingsystem.teacher.dto;

import org.springframework.lang.NonNull;

public record AddTeacherDto(
        @NonNull String firstName,
        @NonNull String lastName,
        String middleName,
        String email,
        int phoneNumber,
        String dateOfBirth
) {
}
