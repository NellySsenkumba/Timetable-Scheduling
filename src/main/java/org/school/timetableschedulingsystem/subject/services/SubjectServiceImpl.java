package org.school.timetableschedulingsystem.subject.services;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.exceptions.MissingFieldsException;
import org.school.timetableschedulingsystem.models.database.Subject;
import org.school.timetableschedulingsystem.subject.SubjectRepository;
import org.school.timetableschedulingsystem.subject.dto.AddSubjectDto;
import org.school.timetableschedulingsystem.subject.dto.SubjectResponseDto;
import org.school.timetableschedulingsystem.subject.mapper.SubjectResponseMapper;
import org.school.timetableschedulingsystem.utils.CustomUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;


    @Override
    public List<SubjectResponseDto> allSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().map(SubjectResponseMapper::mapToDto).toList();
    }

    @Override
    public SubjectResponseDto addSubject(ClientRequest request) {
        Map<String, Object> data = request.data();
        Field[] fields = AddSubjectDto.class.getDeclaredFields();
        List<String> requiredFields = Arrays.stream(fields).map(Field::getName).toList();
        if (!requiredFields.stream().allMatch(data::containsKey)) {
            throw new MissingFieldsException();
        }

        Subject subject = Subject.builder()
                .description((String) data.get("description"))
                .name((String) data.get("name"))
                .build();
        subject = subjectRepository.saveAndFlush(subject);
        return SubjectResponseMapper.mapToDto(subject);
    }

    @Override
    public SubjectResponseDto updateSubject(ClientRequest request) {
        Map<String, Object> data = request.data();
        if (!data.containsKey("id")) {
            throw new MissingFieldsException();
        }
        long subjectId = CustomUtils.convertSubjectIdToLong(data.get("id"));

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(
                () -> new IllegalArgumentException("Subject does not exist")
        );
        if (data.containsKey("name")) {
            subject.setName((String) data.get("name"));
        }
        if (data.containsKey("description")) {
            subject.setDescription((String) data.get("description"));
        }
        subject = subjectRepository.saveAndFlush(subject);
        return SubjectResponseMapper.mapToDto(subject);
    }

    @Override
    public String deleteSubject(ClientRequest request) {
        Map<String, Object> data = request.data();
        if (!data.containsKey("id")) {
            throw new MissingFieldsException();
        }
        long subjectId = CustomUtils.convertSubjectIdToLong(data.get("id"));
        if (!subjectRepository.existsById(subjectId)) {
            throw new IllegalArgumentException("Subject does not exist");
        }
        subjectRepository.deleteById(subjectId);
        return "Subject Deleted";
    }
}
