package org.school.timetableschedulingsystem.timeslot.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record TimeSlotIdResponse(
        DayOfWeek Day,
        LocalTime startTime,
        LocalTime endTime
) {
}
