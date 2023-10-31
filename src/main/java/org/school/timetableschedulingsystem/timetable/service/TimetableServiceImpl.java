package org.school.timetableschedulingsystem.timetable.service;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.exceptions.MissingFieldsException;
import org.school.timetableschedulingsystem.exceptions.NotFoundException;
import org.school.timetableschedulingsystem.lesson.repository.LessonRepository;
import org.school.timetableschedulingsystem.models.database.Lesson;
import org.school.timetableschedulingsystem.models.database.Teacher;
import org.school.timetableschedulingsystem.models.database.Timeslot;
import org.school.timetableschedulingsystem.teacher.TeacherRepository;
import org.school.timetableschedulingsystem.timeslot.TimeslotRepository;
import org.school.timetableschedulingsystem.timetable.dto.TeacherTimetableRequest;
import org.school.timetableschedulingsystem.timetable.dto.TimetableResponse;
import org.school.timetableschedulingsystem.utils.CustomUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final TimeslotRepository timeslotRepository;

    @Override
    public Collection<TimetableResponse> viewTimetables(ClientRequest clientRequest) {
        List<TimetableResponse> response = new ArrayList<>();
        List<Lesson> lessons= lessonRepository.findAll();
        lessons.forEach(
                lesson -> timeslotRepository.findByLessonsContaining(lesson).forEach(
                        timeslot -> response.add(new TimetableResponse(
                                lesson.getId().getTeacher().getFirstName() + " " + lesson.getId().getTeacher().getLastName(),
                                lesson.getId().getSubject().getName(),
                                lesson.getId().getStream().getName(),
                                timeslot.getId().getDay(),
                                timeslot.getId().getStartTime().toString(),
                                timeslot.getId().getEndTime().toString()

                        )
                )
        ));


        return response;
    }

    @Override
    public List<TimetableResponse> viewTeacherTimetable(ClientRequest clientRequest) {
        Map<String, Object> data = clientRequest.data();
        Field[] requiredFields = TeacherTimetableRequest.class.getDeclaredFields();
        if (!Arrays.stream(requiredFields).allMatch(field -> data.containsKey(field.getName()))) {
            throw new MissingFieldsException();
        }
        List<TimetableResponse> response = new ArrayList<>();
        Teacher teacher = teacherRepository.findById(CustomUtils.convertStringToLong(data.get("teacherId")))
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
        List<Timeslot> timeslots = timeslotRepository.findAllByLessons_Id_Teacher(teacher);
        timeslots.forEach(
                timeslot -> timeslot.getLessons().stream().filter(lesson -> lesson.getId().getTeacher().equals(teacher))
                        .forEach(
                                lesson -> response.add(new TimetableResponse(
                                        teacher.getFirstName() + " " + teacher.getLastName(),
                                        lesson.getId().getSubject().getName(),
                                        lesson.getId().getStream().getName(),
                                        timeslot.getId().getDay(),
                                        timeslot.getId().getStartTime().toString(),
                                        timeslot.getId().getEndTime().toString()

                                ))
                        )
        );

        return response;
    }
}
