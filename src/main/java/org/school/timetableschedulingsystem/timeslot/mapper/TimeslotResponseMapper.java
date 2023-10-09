package org.school.timetableschedulingsystem.timeslot.mapper;

import jakarta.persistence.Column;
import org.school.timetableschedulingsystem.models.database.Timeslot;
import org.school.timetableschedulingsystem.timeslot.dto.TimeslotResponse;
import org.springframework.stereotype.Component;

@Component
public class TimeslotResponseMapper {
    private TimeslotResponseMapper(){
    }
    public static TimeslotResponse mapToResponse(Timeslot timeslot){
        return new TimeslotResponse(
                timeslot.getId().getDay().name(),
                timeslot.getId().getStartTime().toString(),
                timeslot.getId().getEndTime().toString()
        );
    }
}
