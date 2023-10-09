package org.school.timetableschedulingsystem.lesson.dto;

public record AssignLessonResponseDto(
        String teacherName,
        String subjectName,
        String streamName
) {
}
