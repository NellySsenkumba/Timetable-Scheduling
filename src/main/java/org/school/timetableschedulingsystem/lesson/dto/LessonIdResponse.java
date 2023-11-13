package org.school.timetableschedulingsystem.lesson.dto;

public record LessonIdResponse(
        Long teacherId,
        Long subjectId,
        Long streamId
) {
}
