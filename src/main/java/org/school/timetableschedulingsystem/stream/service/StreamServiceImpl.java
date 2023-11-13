package org.school.timetableschedulingsystem.stream.service;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.exceptions.MissingFieldsException;
import org.school.timetableschedulingsystem.models.database.Stream;
import org.school.timetableschedulingsystem.stream.StreamRepository;
import org.school.timetableschedulingsystem.stream.dto.AddStreamRequest;
import org.school.timetableschedulingsystem.stream.dto.AddStreamResponse;
import org.school.timetableschedulingsystem.stream.dto.StreamResponse;
import org.school.timetableschedulingsystem.stream.mapper.StreamResponseMapper;
import org.school.timetableschedulingsystem.teacher.TeacherRepository;
import org.school.timetableschedulingsystem.utils.CustomUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StreamServiceImpl implements StreamService {
    private final TeacherRepository teacherRepository;
    private final StreamRepository streamRepository;

    public AddStreamResponse addStream(ClientRequest request) {
        Map<String, Object> data = request.data();
        Field[] fields = AddStreamRequest.class.getDeclaredFields();
        if (!Arrays.stream(fields).map(Field::getName).allMatch(data::containsKey)) {
            throw new MissingFieldsException();
        }

        Stream stream = Stream.builder()
                .name((String) data.get("name"))
                .classTeacher(
                        teacherRepository.findById(
                                CustomUtils.convertStringToLong(data.get("class_teacher_id"))
                        ).orElseThrow(() ->
                                new IllegalArgumentException("Teacher does not exist")
                        )
                )
                .build();
        return StreamResponseMapper.mapToResponse(streamRepository.saveAndFlush(stream));
    }

    @Override
    public Collection<StreamResponse> allStreams() {
        return streamRepository.findAll().stream()
                .map(StreamResponseMapper::mapToStreamResponse).toList();
    }

    @Override
    public AddStreamResponse updateStream(ClientRequest clientRequest) {
        var data = clientRequest.data();
        if (!data.containsKey("id")) {
            throw new MissingFieldsException();
        }
        long streamId = CustomUtils.convertStringToLong(data.get("id"));

        Stream stream = streamRepository.findById(streamId).orElseThrow(
                () -> new IllegalArgumentException("Stream does not exist")
        );
        if (data.containsKey("name")) {
            stream.setName((String) data.get("name"));
        }
        if (data.containsKey("class_teacher_id")) {
            stream.setClassTeacher(
                    teacherRepository.findById(
                            CustomUtils.convertStringToLong(data.get("class_teacher_id"))
                    ).orElseThrow(() ->
                            new IllegalArgumentException("Teacher does not exist")
                    )
            );
        }
        stream=streamRepository.saveAndFlush(stream);
        return new AddStreamResponse(stream.getName(), stream.getClassTeacher().getFirstName() + " " + stream.getClassTeacher().getLastName());
    }

    @Override
    public String deleteStream(ClientRequest clientRequest) {
        var data = clientRequest.data();
        if (!data.containsKey("id")) {
            throw new MissingFieldsException();
        }
        long streamId = CustomUtils.convertStringToLong(data.get("id"));
        if (!streamRepository.existsById(streamId)) {
            throw new IllegalArgumentException("Stream does not exist");
        }
        streamRepository.deleteById(streamId);
        return "Stream has been deleted";
    }

}
