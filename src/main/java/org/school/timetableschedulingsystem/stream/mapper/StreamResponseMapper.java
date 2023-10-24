package org.school.timetableschedulingsystem.stream.mapper;

import org.school.timetableschedulingsystem.models.database.Stream;
import org.school.timetableschedulingsystem.stream.dto.AddStreamResponse;
import org.school.timetableschedulingsystem.stream.dto.StreamResponse;

public class StreamResponseMapper {
    private StreamResponseMapper() {
    }
    public static AddStreamResponse mapToResponse(Stream stream) {
        return new AddStreamResponse(
                stream.getName(),
                stream.getClassTeacher().getFirstName()

        );
    }
     public static StreamResponse mapToStreamResponse(Stream stream) {
        return new StreamResponse(
                stream.getName(),
                stream.getClassTeacher().getFirstName()

        );
    }
}
