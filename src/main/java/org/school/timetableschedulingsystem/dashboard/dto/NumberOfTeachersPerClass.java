package org.school.timetableschedulingsystem.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NumberOfTeachersPerClass(
        @JsonProperty("class")
        String classRoom,
        long numberOfTeachers

) {
}
