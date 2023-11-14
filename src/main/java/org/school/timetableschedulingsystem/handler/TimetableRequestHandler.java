package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.timetable.service.TimetableService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimetableRequestHandler extends RequestHandler{
    private final TimetableService timetableService;
    @Override
    Object handleRequest(ClientRequest clientRequest) {
        return switch (clientRequest.action()) {
            case "view-timetables" -> timetableService.viewTimetables(clientRequest);
            case "teacher-timetable" -> timetableService.viewTeacherTimetable(clientRequest);
            default -> nextRequestHandler.handleRequest(clientRequest);
        };

    }
}
