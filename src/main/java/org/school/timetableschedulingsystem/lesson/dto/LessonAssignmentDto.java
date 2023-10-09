package org.school.timetableschedulingsystem.lesson.dto;

import org.springframework.lang.NonNull;

public record LessonAssignmentDto(
        @NonNull String teacherId,
        @NonNull String  subjectId,
        @NonNull String  streamId,
        @NonNull String  hoursPerWeek

) {
}
