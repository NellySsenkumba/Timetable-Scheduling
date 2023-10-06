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
        switch (request.action()) {
            case "all-subjects": {
                return subjectServiceImpl.allSubjects();
            }
            case "add-subject": {
                return subjectServiceImpl.addSubject(request);
            }
            case "delete-subject": {
                return subjectServiceImpl.deleteSubject(request);
            }
            case "update-subject": {
                return subjectServiceImpl.updateSubject(request);
            }
            default: {
                return nextRequestHandler.handleRequest(request);
            }
        }

    }


}
