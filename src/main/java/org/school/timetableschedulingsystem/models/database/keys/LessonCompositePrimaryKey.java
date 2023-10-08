package org.school.timetableschedulingsystem.models.database.keys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.school.timetableschedulingsystem.models.database.Stream;
import org.school.timetableschedulingsystem.models.database.Subject;
import org.school.timetableschedulingsystem.models.database.Teacher;

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
