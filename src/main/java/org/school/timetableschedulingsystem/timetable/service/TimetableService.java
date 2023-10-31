package org.school.timetableschedulingsystem.timetable.service;

import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.timetable.dto.TimetableResponse;

import java.util.Collection;
import java.util.List;

public interface TimetableService {
    Collection<TimetableResponse> viewTimetables(ClientRequest clientRequest);

    List<TimetableResponse> viewTeacherTimetable(ClientRequest clientRequest);
}
