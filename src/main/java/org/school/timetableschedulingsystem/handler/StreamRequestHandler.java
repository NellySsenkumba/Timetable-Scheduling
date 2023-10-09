package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.stream.service.StreamService;
import org.school.timetableschedulingsystem.stream.service.StreamServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamRequestHandler extends RequestHandler {
    private final StreamServiceImpl streamService;
    @Override
    Object handleRequest(ClientRequest clientRequest) {
        switch (clientRequest.action()) {
            case "add-stream":{
                return streamService.addStream(clientRequest);
            }

            default:
                return nextRequestHandler.handleRequest(clientRequest);
        }
    }
}
