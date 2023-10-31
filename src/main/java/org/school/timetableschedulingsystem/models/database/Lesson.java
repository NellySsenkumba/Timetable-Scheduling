package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.school.timetableschedulingsystem.models.database.keys.LessonCompositePrimaryKey;

import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Lesson {
    @EmbeddedId
    private LessonCompositePrimaryKey id;

    @Column(nullable = false)
    private int hoursPerWeek;


}
