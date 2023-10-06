package org.school.timetableschedulingsystem.subject.services;

import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.models.database.Subject;
import org.school.timetableschedulingsystem.subject.dto.AddSubjectDto;
import org.school.timetableschedulingsystem.subject.dto.SubjectResponseDto;

import java.util.List;

public interface SubjectService {
    List<SubjectResponseDto> allSubjects();
    SubjectResponseDto addSubject(ClientRequest request);
    SubjectResponseDto updateSubject(ClientRequest request);

    String deleteSubject(ClientRequest request);
}
