package org.school.timetableschedulingsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
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


    Available time(only for part-timers)

    Status
    Set<Subject> subjects; //<dono whether this    should be     here on    in the Subjects table>


}
