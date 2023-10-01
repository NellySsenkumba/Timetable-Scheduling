package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.school.timetableschedulingsystem.models.enums.DaysOfTheWeek;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private int phoneNumber;
    private String dateOfBirth;

    private LocalDateTime registeredOn;


    Set<DaysOfTheWeek> availableTime;//(only for part-timers)

    private String status;

    @ManyToMany
    Set<Subject> subjects; //<dono whether this    should be     here on    in the Subjects table>


}
