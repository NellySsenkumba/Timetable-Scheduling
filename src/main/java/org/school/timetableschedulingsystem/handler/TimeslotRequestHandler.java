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
        switch (clientRequest.action()) {
            case "add-timeslot":
                return timeslotService.addTimeslot(clientRequest);
            case "all-timeslot":
                return timeslotService.getTimeslot();
            case "assign-lesson":
                return timeslotService.assign();

            default:
                return this.nextRequestHandler.handleRequest(clientRequest);
        }
    }
}
