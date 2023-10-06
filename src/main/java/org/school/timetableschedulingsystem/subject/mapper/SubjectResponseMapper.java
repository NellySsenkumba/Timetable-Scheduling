package org.school.timetableschedulingsystem.subject.mapper;

import org.school.timetableschedulingsystem.models.database.Subject;
import org.school.timetableschedulingsystem.subject.dto.SubjectResponseDto;
import org.springframework.stereotype.Component;


public class SubjectResponseMapper {
    private SubjectResponseMapper() {
    }

    public static SubjectResponseDto mapToDto(Subject subject) {
        return new SubjectResponseDto(
                subject.getName(),
                subject.getDescription()
        );
    }
}
