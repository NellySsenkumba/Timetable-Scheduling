package org.school.timetableschedulingsystem.dashboard;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.dashboard.dto.DashboardClassTeacherResponse;
import org.school.timetableschedulingsystem.dashboard.dto.DashboardResponse;
import org.school.timetableschedulingsystem.dashboard.dto.NumberOfTeachersPerClass;
import org.school.timetableschedulingsystem.lesson.repository.LessonRepository;
import org.school.timetableschedulingsystem.models.database.Lesson;
import org.school.timetableschedulingsystem.models.database.Stream;
import org.school.timetableschedulingsystem.models.database.Teacher;
import org.school.timetableschedulingsystem.stream.StreamRepository;
import org.school.timetableschedulingsystem.subject.SubjectRepository;
import org.school.timetableschedulingsystem.teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final StreamRepository streamRepository;
    private final LessonRepository lessonRepository;

    public DashboardResponse show(Map<String, Object> data) {

        return new DashboardResponse(
                teacherRepository.findAll().size(),
                subjectRepository.findAll().size(),
                lessonRepository.findAll().size(),
                streamRepository.findAll().size(),
                streamRepository.findAll().stream().map(stream -> new DashboardClassTeacherResponse(
                        stream.getClassTeacher().getFirstName(),
                        stream.getName()
                )).toList()

        );

    }


    public List<NumberOfTeachersPerClass> numberOfTeachersPerClass() {
        Set<NumberOfTeachersPerClass> response = new HashSet<>();
        List<Stream> streams = streamRepository.findAll();
        streams.forEach(stream -> {
            List<Lesson> lessons = lessonRepository.findAllById_Stream(stream);
            List<Lesson> uniqueTeacherLessons = lessons.stream()
                    .collect(Collectors.groupingBy(
                            lesson -> lesson.getId().getTeacher(), // Grouping by teacher
                            Collectors.collectingAndThen(
                                    Collectors.toList(),
                                    lessonList -> lessonList.stream()
                                            .findFirst() // Take the first occurrence of each teacher
                                            .orElse(null) // If empty, return null
                            )
                    ))
                    .values()
                    .stream()
                    .filter(Objects::nonNull)
                    .toList();
            response.add(new NumberOfTeachersPerClass(stream.getClassRoom(), uniqueTeacherLessons.size()));

        });


        return response.stream().toList();
    }
}
