package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name="teacher_stream_subject_unique_key",columnNames = {"teacher_id","stream_id","subject_id"}))
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Teacher teacher;

    @OneToOne
    private Stream stream;


    @OneToOne
    private Subject subject;
    private int hoursPerWeek;
}
