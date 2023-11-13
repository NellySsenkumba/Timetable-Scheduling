package org.school.timetableschedulingsystem.lesson.dto;

public record AssignLessonResponseDto(
        LessonIdResponse id,
        String teacherName,
        String subjectName,
        String streamName
) {
}
