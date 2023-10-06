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

        switch (request.action()) {
            case "add-teacher":
                return teacherService.addTeacher(request);

            case "all-teacher": {
                return teacherService.allTeachers();
            }

            default: {
                return nextRequestHandler.handleRequest(request);
            }
        }


    }


}
