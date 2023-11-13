package org.school.timetableschedulingsystem.timeslot.service;

import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.timeslot.dto.TimeslotResponse;

import java.util.List;

public interface TimeslotService {
    TimeslotResponse addTimeslot(ClientRequest clientRequest);
    List<TimeslotResponse> getTimeslot();
    TimeslotResponse updateTimeslot(ClientRequest clientRequest);

    String deleteTimeslot(ClientRequest clientRequest);

    String assign();


}
