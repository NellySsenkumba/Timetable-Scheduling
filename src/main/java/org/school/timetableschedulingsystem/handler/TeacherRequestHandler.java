package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.exceptions.MissingFieldsException;
import org.school.timetableschedulingsystem.models.database.Teacher;
import org.school.timetableschedulingsystem.teacher.TeacherService;
import org.school.timetableschedulingsystem.teacher.dto.AddTeacherDto;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TeacherServiceHandler extends ServiceHandler {

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
                return nextServiceHandler.handleRequest(request);
            }
        }


    }


}
