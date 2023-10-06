package org.school.timetableschedulingsystem.teacher;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.exceptions.MissingFieldsException;
import org.school.timetableschedulingsystem.models.database.Teacher;
import org.school.timetableschedulingsystem.teacher.dto.AddTeacherDto;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> allTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher addTeacher(ClientRequest request) {
        Map<String, Object> data = request.data();
        Field[] fields = AddTeacherDto.class.getDeclaredFields();
        List<String> requiredFields = Arrays.stream(fields).map(Field::getName).toList();

        if (!requiredFields.stream().allMatch(data::containsKey)) {
            throw new MissingFieldsException();
        }
        Teacher teacher = Teacher.builder()
                .firstName((String) data.get("firstname"))
                .lastName((String) data.get("lastname"))
                .lastName((String) data.get("lastname"))
                .middleName((String) data.get("middlename"))
                .email((String) data.get("email"))
                .build();

        return teacherRepository.saveAndFlush(teacher);
    }
}
