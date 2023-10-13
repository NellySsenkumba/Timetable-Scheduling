package org.school.timetableschedulingsystem.scheduler;

import java.time.LocalTime;

public record TimeSlotsMap(
        LocalTime startTime,
        LocalTime endTime
) {

}
