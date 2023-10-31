package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RequestHandlerSetter {
    private final TeacherRequestHandler teacherRequestHandler;
    private final SubjectRequestHandler subjectRequestHandler;
    private final LessonRequestHandler lessonRequestHandler;
    private final LastRequestHandler lastRequestHandler;
    private final StreamRequestHandler streamRequestHandler;
    private final TimeslotRequestHandler timeslotRequestHandler;
    private final TimetableRequestHandler timetableRequestHandler;

    @Bean
    public void setOrder() {
        teacherRequestHandler.setNextRequestHandler(subjectRequestHandler);
        subjectRequestHandler.setNextRequestHandler(lessonRequestHandler);
        lessonRequestHandler.setNextRequestHandler(streamRequestHandler);
        streamRequestHandler.setNextRequestHandler(timeslotRequestHandler);
        timeslotRequestHandler.setNextRequestHandler(timetableRequestHandler);
        timetableRequestHandler.setNextRequestHandler(lastRequestHandler);

    }
}
