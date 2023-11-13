package org.school.timetableschedulingsystem.lesson.mapper;

import org.school.timetableschedulingsystem.lesson.dto.AssignLessonResponseDto;
import org.school.timetableschedulingsystem.lesson.dto.LessonIdResponse;
import org.school.timetableschedulingsystem.models.database.Lesson;

public class AssignLessonDtoMapper {
    private AssignLessonDtoMapper() {
    }

    public static AssignLessonResponseDto mapToDto(Lesson lesson) {
        return new AssignLessonResponseDto(
                new LessonIdResponse(
                        lesson.getId().getTeacher().getId(),
                        lesson.getId().getSubject().getId(),
                        lesson.getId().getStream().getId()),
                lesson.getId().getTeacher().getFirstName(),
                lesson.getId().getSubject().getName(),
                lesson.getId().getStream().getName()
        );
    }
}
