package org.school.timetableschedulingsystem.dashboard;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.dashboard.dto.DashboardClassTeacherResponse;
import org.school.timetableschedulingsystem.dashboard.dto.DashboardResponse;
import org.school.timetableschedulingsystem.lesson.repository.LessonRepository;
import org.school.timetableschedulingsystem.stream.StreamRepository;
import org.school.timetableschedulingsystem.subject.SubjectRepository;
import org.school.timetableschedulingsystem.teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

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
}
