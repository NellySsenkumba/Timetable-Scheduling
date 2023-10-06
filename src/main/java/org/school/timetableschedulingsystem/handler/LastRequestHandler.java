package org.school.timetableschedulingsystem.handler;

import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.springframework.stereotype.Service;


@Service
public class LastRequestHandler extends RequestHandler {
    @Override
    public Object handleRequest(ClientRequest clientRequest) {
        return "invalid action";
    }
}
