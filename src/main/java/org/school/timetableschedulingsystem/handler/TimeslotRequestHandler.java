package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.timeslot.service.TimeslotService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeslotRequestHandler extends RequestHandler {
    private final TimeslotService timeslotService;

    @Override
    Object handleRequest(ClientRequest clientRequest) {
        return switch (clientRequest.action()) {
            case "add-timeslot" -> timeslotService.addTimeslot(clientRequest);
            case "all-timeslot" -> timeslotService.getTimeslot();
            case "delete-timeslot" -> timeslotService.deleteTimeslot(clientRequest);
            case "assign-lesson" -> timeslotService.assign();
            default -> this.nextRequestHandler.handleRequest(clientRequest);
        };
    }
}
