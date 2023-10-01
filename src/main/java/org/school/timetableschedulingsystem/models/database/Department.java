package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    private long id;
    private String name;
    @OneToOne
    private Teacher headOfDepartment;
    private LocalDateTime createdOn;

    @OneToMany(mappedBy = "department")
    private Set<Subject> subjects;

}
