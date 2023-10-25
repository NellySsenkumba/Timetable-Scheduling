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


        switch (clientRequest.action()) {
            case "add-lesson":
                return lessonService.addLesson(clientRequest);
            case "all-lessons":
                return this.lessonService.allLessons();
            default:
                return nextRequestHandler.handleRequest(clientRequest);
        }

    }
}
