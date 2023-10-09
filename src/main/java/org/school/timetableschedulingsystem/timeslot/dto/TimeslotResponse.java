package org.school.timetableschedulingsystem.timeslot.dto;

public record TimeslotResponse(
        String day,
        String startTime,
        String endTime
) {
}
