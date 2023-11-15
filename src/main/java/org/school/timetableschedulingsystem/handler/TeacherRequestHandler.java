package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.teacher.TeacherService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherRequestHandler extends RequestHandler {

    private final TeacherService teacherService;

    @Override
    public Object handleRequest(ClientRequest request) {

        return switch (request.action()) {
            case "add-teacher" -> teacherService.addTeacher(request);
            case "all-teacher" -> teacherService.allTeachers();
            case "update-teacher" -> teacherService.updateTeacher(request);
            case "delete-teacher" -> teacherService.deleteTeacher(request);
            case "get-teacher" -> teacherService.singleTeacher(request);
            default -> nextRequestHandler.handleRequest(request);
        };


    }


}
