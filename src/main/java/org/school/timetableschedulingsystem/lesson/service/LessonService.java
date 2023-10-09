package org.school.timetableschedulingsystem.lesson.service;

import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.lesson.dto.AssignLessonResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface LessonService {
    AssignLessonResponseDto addLesson(ClientRequest clientRequest);
}
