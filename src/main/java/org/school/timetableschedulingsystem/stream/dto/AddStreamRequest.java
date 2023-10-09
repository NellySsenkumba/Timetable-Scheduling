package org.school.timetableschedulingsystem.stream.dto;

import org.springframework.lang.NonNull;

public record AddStreamRequest(
        @NonNull String name,
        String class_teacher_id
) {
}
