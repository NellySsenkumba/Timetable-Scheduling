package org.school.timetableschedulingsystem.lesson.service;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.exceptions.MissingFieldsException;
import org.school.timetableschedulingsystem.lesson.dto.AssignLessonResponseDto;
import org.school.timetableschedulingsystem.lesson.dto.LessonAssignmentDto;
import org.school.timetableschedulingsystem.lesson.mapper.AssignLessonDtoMapper;
import org.school.timetableschedulingsystem.lesson.repository.LessonRepository;
import org.school.timetableschedulingsystem.models.database.Lesson;
import org.school.timetableschedulingsystem.models.database.keys.LessonCompositePrimaryKey;
import org.school.timetableschedulingsystem.stream.StreamRepository;
import org.school.timetableschedulingsystem.subject.SubjectRepository;
import org.school.timetableschedulingsystem.teacher.TeacherRepository;
import org.school.timetableschedulingsystem.utils.CustomUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final TeacherRepository teacherRepository;
    private final StreamRepository streamRepository;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;


    @Override
    public AssignLessonResponseDto addLesson(ClientRequest clientRequest) {
        Map<String, Object> data = clientRequest.data();
        Field[] fields = LessonAssignmentDto.class.getDeclaredFields();
        if (!Arrays.stream(fields).map(Field::getName).allMatch(data::containsKey)) {
            throw new MissingFieldsException();
        }
        CustomUtils.convertStringToLong(data.get("subjectId"));
        Lesson lesson = Lesson.builder()
                .hoursPerWeek((int) data.get("hoursPerWeek"))
                .id(
                        LessonCompositePrimaryKey.builder()
                                .teacher(
                                        teacherRepository.findById(
                                                CustomUtils.convertStringToLong(data.get("teacherId"))
                                        ).orElseThrow(
                                                () -> new IllegalArgumentException("Teacher does not exist")
                                        ))
                                .stream(
                                        streamRepository.findById(
                                                CustomUtils.convertStringToLong(data.get("streamId"))
                                        ).orElseThrow(
                                                () -> new IllegalArgumentException("Stream does not exist")
                                        ))
                                .subject(
                                        subjectRepository.findById(
                                                CustomUtils.convertStringToLong(data.get("subjectId"))
                                        ).orElseThrow(
                                                () -> new IllegalArgumentException("Subject does not exist"))
                                )

                                .build()
                )
                .build();
        return AssignLessonDtoMapper.mapToDto(lessonRepository.saveAndFlush(lesson));
    }

    @Override
    public Collection<AssignLessonResponseDto> allLessons() {
        return lessonRepository.findAll().stream()
                .map(AssignLessonDtoMapper::mapToDto).toList();
    }

    @Override
    public AssignLessonResponseDto updateLesson(ClientRequest clientRequest) {
        var data = clientRequest.data();
        if (!data.containsKey("teacherId") || !data.containsKey("subjectId") || !data.containsKey("streamId")) {
            throw new MissingFieldsException();
        }
        long teacherId = CustomUtils.convertStringToLong(data.get("teacherId"));
        long subjectId = CustomUtils.convertStringToLong(data.get("subjectId"));
        long streamId = CustomUtils.convertStringToLong(data.get("streamId"));
        Lesson lesson = lessonRepository.findById(new LessonCompositePrimaryKey(
                teacherRepository.findById(teacherId).orElseThrow(
                        () -> new IllegalArgumentException("Teacher does not exist")
                ),
                streamRepository.findById(streamId).orElseThrow(
                        () -> new IllegalArgumentException("Stream does not exist")
                ),
                subjectRepository.findById(subjectId).orElseThrow(
                        () -> new IllegalArgumentException("Subject does not exist")
                )
        )).orElseThrow(
                () -> new IllegalArgumentException("Lesson does not exist")
        );

        if (data.containsKey("hoursPerWeek")) {
            lesson.setHoursPerWeek((int) data.get("hoursPerWeek"));
        }
        return AssignLessonDtoMapper.mapToDto(lessonRepository.saveAndFlush(lesson));

    }

    @Override
    public String deleteLesson(ClientRequest clientRequest) {
        var data = clientRequest.data();
        if (!data.containsKey("teacherId") || !data.containsKey("subjectId") || !data.containsKey("streamId")) {
            throw new MissingFieldsException();
        }
        long teacherId = CustomUtils.convertStringToLong(data.get("teacherId"));
        long subjectId = CustomUtils.convertStringToLong(data.get("subjectId"));
        long streamId = CustomUtils.convertStringToLong(data.get("streamId"));
        Lesson lesson = lessonRepository.findById(new LessonCompositePrimaryKey(
                teacherRepository.findById(teacherId).orElseThrow(
                        () -> new IllegalArgumentException("Teacher does not exist")
                ),
                streamRepository.findById(streamId).orElseThrow(
                        () -> new IllegalArgumentException("Stream does not exist")
                ),
                subjectRepository.findById(subjectId).orElseThrow(
                        () -> new IllegalArgumentException("Subject does not exist")
                )
        )).orElseThrow(
                () -> new IllegalArgumentException("Lesson does not exist")
        );
        lessonRepository.delete(lesson);
        return "lesson has been deleted";
    }
}
