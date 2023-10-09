package org.school.timetableschedulingsystem.models.database.keys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeslotCompositePrimaryKey implements java.io.Serializable {
    private LocalTime startTime;
    private LocalTime endTime;
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
}
