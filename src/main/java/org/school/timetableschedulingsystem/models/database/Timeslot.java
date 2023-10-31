package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.school.timetableschedulingsystem.models.database.keys.TimeslotCompositePrimaryKey;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Timeslot {
    @EmbeddedId
    private TimeslotCompositePrimaryKey id;

    @ManyToMany(
            cascade = jakarta.persistence.CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Lesson> lessons;

}
