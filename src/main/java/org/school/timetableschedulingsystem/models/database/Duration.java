package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Duration {
    @Id
    private long id;
    @ManyToOne
    private Class aclass;
    @ManyToOne
    private Subject subject;
    private int hoursPerWeek;
}
