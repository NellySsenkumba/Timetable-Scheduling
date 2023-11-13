package org.school.timetableschedulingsystem.timeslot.dto;

public record TimeslotResponse(
        TimeSlotIdResponse id,
        String day,
        String startTime,
        String endTime
) {
}
