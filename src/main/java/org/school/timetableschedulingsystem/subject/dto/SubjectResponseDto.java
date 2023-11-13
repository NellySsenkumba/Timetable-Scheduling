package org.school.timetableschedulingsystem.subject.dto;

import org.springframework.lang.NonNull;

public record SubjectResponseDto(
        long id,
        @NonNull String name,
        @NonNull String description
) {
}
