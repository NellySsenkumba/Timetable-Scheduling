package org.school.timetableschedulingsystem.timeslot.service;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.exceptions.MissingFieldsException;
import org.school.timetableschedulingsystem.exceptions.NotFoundException;
import org.school.timetableschedulingsystem.lesson.repository.LessonRepository;
import org.school.timetableschedulingsystem.models.database.Stream;
import org.school.timetableschedulingsystem.models.database.Timeslot;
import org.school.timetableschedulingsystem.models.database.keys.TimeslotCompositePrimaryKey;
import org.school.timetableschedulingsystem.scheduler.Assign;
import org.school.timetableschedulingsystem.stream.StreamRepository;
import org.school.timetableschedulingsystem.timeslot.TimeslotRepository;
import org.school.timetableschedulingsystem.timeslot.dto.AddTimeslotDto;
import org.school.timetableschedulingsystem.timeslot.dto.TimeslotResponse;
import org.school.timetableschedulingsystem.timeslot.mapper.TimeslotResponseMapper;
import org.school.timetableschedulingsystem.utils.CustomUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TimeslotServiceImp implements TimeslotService {
    private final TimeslotRepository timeslotRepository;
    private final StreamRepository streamRepository;
    private final Assign assign;
    private final LessonRepository lessonRepository;

    public TimeslotResponse addTimeslot(ClientRequest clientRequest) {
        Map<String, Object> data = clientRequest.data();
        Field[] required = AddTimeslotDto.class.getDeclaredFields();
        if (!Arrays.stream(required).map(Field::getName).allMatch(data::containsKey)) {
            throw new MissingFieldsException();
        }
        Timeslot timeslot = Timeslot.builder()
                .id(
                        TimeslotCompositePrimaryKey.builder()
                                .day(DayOfWeek.valueOf((String) data.get("day")))
                                .startTime(LocalTime.parse((String) data.get("startTime")))
                                .endTime(LocalTime.parse((String) data.get("endTime")))
                                .build()
                )
                .build();
        return TimeslotResponseMapper.mapToResponse(timeslotRepository.saveAndFlush(timeslot));
    }

    @Override
    public List<TimeslotResponse> getTimeslot() {
        return timeslotRepository.findAll().stream()
                .map(TimeslotResponseMapper::mapToResponse)
                .toList()
                ;

    }

    @Override
    public TimeslotResponse updateTimeslot(ClientRequest clientRequest) {
        var data = clientRequest.data();
        if (!data.containsKey("startTime") || !data.containsKey("endTime") || !data.containsKey("day")) {
            throw new MissingFieldsException();
        }
        LocalTime startTime = (LocalTime) data.get("startTime");
        LocalTime endTime = (LocalTime) data.get("endTime");
        DayOfWeek day = (DayOfWeek) data.get("day");
        Timeslot timeslot = timeslotRepository.findById(new TimeslotCompositePrimaryKey(
                startTime,
                endTime,
                day
        )).orElseThrow(() -> new NotFoundException("Timeslot not found"));

        return null;
    }

    @Override
    public String deleteTimeslot(ClientRequest clientRequest) {
        var data = clientRequest.data();
        if (!data.containsKey("startTime") || !data.containsKey("endTime") || !data.containsKey("day")) {
            throw new MissingFieldsException();
        }
        LocalTime startTime = (LocalTime) data.get("startTime");
        LocalTime endTime = (LocalTime) data.get("endTime");
        DayOfWeek day = (DayOfWeek) data.get("day");
        Timeslot timeslot = timeslotRepository.findById(new TimeslotCompositePrimaryKey(
                startTime,
                endTime,
                day
        )).orElseThrow(() -> new NotFoundException("Timeslot not found"));
        timeslotRepository.delete(timeslot);
        return "deleted timeslot";
    }

    @Override
    public String assign() {
        List<Stream> streams = streamRepository.findAll();
        List<Timeslot> timeslots = timeslotRepository.findAll();
        timeslotRepository.saveAll(timeslots.stream()
                .peek(timeslot ->
                        timeslot.getLessons().clear())
                .toList());

        streams.forEach(assign::assignTimeSlot2);
        return "Timetable has been generated";
    }
}
