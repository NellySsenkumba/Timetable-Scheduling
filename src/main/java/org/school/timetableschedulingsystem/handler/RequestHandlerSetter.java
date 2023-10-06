package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RequestHandlerSetter {
    private final TeacherRequestHandler teacherRequestHandler;
    private final SubjectRequestHandler subjectRequestHandler;
    private final LastRequestHandler lastRequestHandler;

    @Bean
    public void setOrder() {
        teacherRequestHandler.setNextRequestHandler(subjectRequestHandler);
        subjectRequestHandler.setNextRequestHandler(lastRequestHandler);

    }
}
