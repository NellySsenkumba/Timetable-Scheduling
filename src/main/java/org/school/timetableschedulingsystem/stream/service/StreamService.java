package org.school.timetableschedulingsystem.stream.service;

import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.stream.dto.AddStreamResponse;
import org.school.timetableschedulingsystem.stream.dto.StreamResponse;
import org.springframework.stereotype.Service;

import java.util.Collection;


public interface StreamService {
    AddStreamResponse addStream(ClientRequest clientRequest);
    Collection<StreamResponse> allStreams();

}

