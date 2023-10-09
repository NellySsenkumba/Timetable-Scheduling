package org.school.timetableschedulingsystem.stream.mapper;

import org.school.timetableschedulingsystem.models.database.Stream;
import org.school.timetableschedulingsystem.stream.dto.AddStreamResponse;

public class StreamResponseMapper {
    public static AddStreamResponse mapToResponse(Stream stream) {
        return new AddStreamResponse(
                stream.getName(),
                stream.getClassTeacher().getFirstName()

        );
    }
}
