package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class LessonCompositePrimaryKey implements Serializable {
    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Stream stream;


    @ManyToOne
    private Subject subject;
}
