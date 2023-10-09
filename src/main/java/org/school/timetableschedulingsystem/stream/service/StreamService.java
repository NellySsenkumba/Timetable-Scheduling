package org.school.timetableschedulingsystem.stream.service;

import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.stream.dto.AddStreamResponse;
import org.springframework.stereotype.Service;


public interface StreamService {
    AddStreamResponse addStream(ClientRequest clientRequest);

}

