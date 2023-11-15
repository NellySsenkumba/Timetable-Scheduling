package org.school.timetableschedulingsystem.teacher.dto;

import org.school.timetableschedulingsystem.models.database.Teacher;
import org.school.timetableschedulingsystem.models.enums.Gender;

import java.time.LocalDate;

public record TeacherResponseDto(
        long id,
        String firstName,
        String middleName,
        String lastName,
        String email,
        String phoneNumber,

        Gender gender

) {
    public static TeacherResponseDto fromTeacher(Teacher teacher) {
        return new TeacherResponseDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getMiddleName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getPhoneNumber(),
                teacher.getGender()
        );
    }
}
