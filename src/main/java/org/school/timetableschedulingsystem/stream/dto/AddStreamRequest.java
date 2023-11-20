package org.school.timetableschedulingsystem.stream.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public record AddStreamRequest(
        @NonNull String name,
        @JsonProperty("class")
        @NonNull String  classRoom,
        String class_teacher_id
) {
}
