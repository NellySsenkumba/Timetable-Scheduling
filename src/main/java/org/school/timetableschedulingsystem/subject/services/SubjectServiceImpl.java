package org.school.timetableschedulingsystem.subject.services;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.subject.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImp {
    private final SubjectRepository subjectRepository;

    public Object allSubjects() {
        return subjectRepository.findAll();
    }
}
