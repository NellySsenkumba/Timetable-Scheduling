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
        return switch (clientRequest.action()) {
            case "add-stream" -> streamService.addStream(clientRequest);
            case "all-streams" -> streamService.allStreams();
            case "update-stream" -> streamService.updateStream(clientRequest);
            case "delete-stream" -> streamService.deleteStream(clientRequest);
            default -> nextRequestHandler.handleRequest(clientRequest);
        };
    }
}
