package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(nullable = false)
    private Teacher classTeacher;

    @Column(nullable = false,name = "class",columnDefinition = "VARCHAR(10) DEFAULT 'PRIMARY 1'")
    private String classRoom;


    @ManyToOne
    @JoinColumn(nullable = true)
    private User updatedBy;

}
