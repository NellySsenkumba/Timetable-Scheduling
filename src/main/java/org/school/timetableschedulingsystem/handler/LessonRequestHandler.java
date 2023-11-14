package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.springframework.stereotype.Service;
import org.school.timetableschedulingsystem.lesson.service.LessonService;

@RequiredArgsConstructor
@Service
public class LessonRequestHandler extends RequestHandler {
    private final LessonService lessonService;

    @Override
    Object handleRequest(ClientRequest clientRequest) {


        return switch (clientRequest.action()) {
            case "add-lesson" -> lessonService.addLesson(clientRequest);
            case "all-lessons" -> this.lessonService.allLessons();
            case "delete-lesson" -> this.lessonService.deleteLesson(clientRequest);
            default -> nextRequestHandler.handleRequest(clientRequest);
        };

    }
}
