package org.school.timetableschedulingsystem.subject.dto;

import org.springframework.lang.NonNull;

public record AddSubjectDto(
        @NonNull String name,
        @NonNull String description
) {
}
