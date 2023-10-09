package org.school.timetableschedulingsystem.lesson.mapper;

import org.school.timetableschedulingsystem.lesson.dto.AssignLessonResponseDto;
import org.school.timetableschedulingsystem.models.database.Lesson;

public class AssignLessonDtoMapper {
    private AssignLessonDtoMapper() {
    }
    public static AssignLessonResponseDto mapToDto(Lesson lesson) {
        return new AssignLessonResponseDto(
                lesson.getId().getTeacher().getFirstName(),
                lesson.getId().getSubject().getName(),
                lesson.getId().getStream().getName()
        );
    }
}
