package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.subject.services.SubjectServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectRequestHandler extends RequestHandler {
    private final SubjectServiceImpl subjectServiceImpl;

    @Override
    public Object handleRequest(ClientRequest request) {
        return switch (request.action()) {
            case "all-subjects" -> subjectServiceImpl.allSubjects();
            case "add-subject" -> subjectServiceImpl.addSubject(request);
            case "delete-subject" -> subjectServiceImpl.deleteSubject(request);
            case "update-subject" -> subjectServiceImpl.updateSubject(request);
            case "get-subject" -> subjectServiceImpl.getSubject(request);
            default -> nextRequestHandler.handleRequest(request);
        };

    }


}
