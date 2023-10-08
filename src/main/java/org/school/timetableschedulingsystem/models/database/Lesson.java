package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.school.timetableschedulingsystem.models.database.keys.LessonCompositePrimaryKey;

@Entity
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name="teacher_stream_subject_unique_key",columnNames = {"teacher_id","stream_id","subject_id"}))
public class Lesson {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LessonCompositePrimaryKey id;
    private int hoursPerWeek;
}
