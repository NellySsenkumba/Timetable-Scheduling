package org.school.timetableschedulingsystem.lesson.service;

import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.lesson.dto.AssignLessonResponseDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface LessonService {
    AssignLessonResponseDto addLesson(ClientRequest clientRequest);

    Collection<AssignLessonResponseDto> allLessons();

    AssignLessonResponseDto updateLesson(ClientRequest clientRequest);

    String deleteLesson(ClientRequest clientRequest);
}
