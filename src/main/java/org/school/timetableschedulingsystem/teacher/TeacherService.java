package org.school.timetableschedulingsystem.teacher;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.exceptions.MissingFieldsException;
import org.school.timetableschedulingsystem.models.database.Teacher;
import org.school.timetableschedulingsystem.models.enums.Gender;
import org.school.timetableschedulingsystem.teacher.dto.AddTeacherDto;
import org.school.timetableschedulingsystem.teacher.dto.TeacherResponseDto;
import org.school.timetableschedulingsystem.utils.CustomUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final DateTimeFormatter formatter;

    public List<TeacherResponseDto> allTeachers() {
        return teacherRepository.findAll().stream().map(
                TeacherResponseDto::fromTeacher
        ).toList(
        );
    }

    public TeacherResponseDto addTeacher(ClientRequest request) {
        Map<String, Object> data = request.data();
        Field[] fields = AddTeacherDto.class.getDeclaredFields();
        List<String> requiredFields = Arrays.stream(fields).map(Field::getName).toList();

        if (!requiredFields.stream().allMatch(data::containsKey)) {
            throw new MissingFieldsException();
        }
        //Todo:try this
        var test = Arrays.stream(fields).map(Field::getName).allMatch(data::containsKey);
        System.getLogger("test").log(System.Logger.Level.INFO, test);


        String dateString = (String) data.get("dateOfBirth");
        LocalDate date = LocalDate.parse(dateString, formatter);

        Teacher teacher = Teacher.builder()
                .firstName((String) data.get("firstName"))
                .lastName((String) data.get("lastName"))
                .lastName((String) data.get("lastName"))
                .middleName((String) data.get("middleName"))
                .email((String) data.get("email"))
                .phoneNumber((int) data.get("phoneNumber"))
                .gender(Gender.valueOf((String) data.get("gender")))
                .dateOfBirth(date)
                .build();

        return TeacherResponseDto.fromTeacher(teacherRepository.saveAndFlush(teacher));
    }

    public TeacherResponseDto updateTeacher(ClientRequest request) {
        Map<String, Object> data = request.data();
        if (!data.containsKey("id")) {
            throw new MissingFieldsException();
        }
        long teacherId = CustomUtils.convertStringToLong(data.get("id"));

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(
                () -> new IllegalArgumentException("Teacher does not exist")
        );
        if (data.containsKey("firstName")) {
            teacher.setFirstName((String) data.get("firstName"));
        }
        if (data.containsKey("lastName")) {
            teacher.setLastName((String) data.get("lastName"));
        }
        if (data.containsKey("middleName")) {
            teacher.setMiddleName((String) data.get("middleName"));
        }
        if (data.containsKey("email")) {
            teacher.setEmail((String) data.get("email"));
        }
        if (data.containsKey("phoneNumber")) {
            teacher.setPhoneNumber((int) data.get("phoneNumber"));
        }
        if (data.containsKey("dateOfBirth")) {
            String dateString = (String) data.get("dateOfBirth");
            LocalDate date = LocalDate.parse(dateString, formatter);
            teacher.setDateOfBirth(date);
        }
        return TeacherResponseDto.fromTeacher(teacherRepository.saveAndFlush(teacher));
    }

    public String deleteTeacher(ClientRequest request) {
        Map<String, Object> data = request.data();
        if (!data.containsKey("id")) {
            throw new MissingFieldsException();
        }
        long teacherId = CustomUtils.convertStringToLong(data.get("id"));
        if (!teacherRepository.existsById(teacherId)) {
            throw new IllegalArgumentException("Teacher does not exist");
        }
        teacherRepository.deleteById(teacherId);
        return "Teacher Deleted";
    }
}
