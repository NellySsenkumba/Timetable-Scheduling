package org.school.timetableschedulingsystem.scheduler;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.lesson.repository.LessonRepository;
import org.school.timetableschedulingsystem.models.database.Lesson;
import org.school.timetableschedulingsystem.models.database.Stream;
import org.school.timetableschedulingsystem.models.database.Subject;
import org.school.timetableschedulingsystem.models.database.Timeslot;
import org.school.timetableschedulingsystem.timeslot.TimeslotRepository;
import org.school.timetableschedulingsystem.timeslot.dto.TimeslotResponse;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RequiredArgsConstructor
@Component
public class Assign {

    private final LessonRepository lessonRepository;
    private final TimeslotRepository timeslotRepository;


    private static final int DAYS = 3;
    private static final int TIME_SLOTS = 12;
    private static final int BREAK_1 = 3;
    private static final int BREAK_2 = 6;
    static String[] subjects = {"Mat", "Eng", "Sci", "SST"};


    static Random rd = new Random();


    public static boolean isAfterOrBeforeBreak(String[][] table, int[] position) {
        int x = position[0];
        int y = position[1];
        int z = position[2];
        if (y == BREAK_1 || y == BREAK_2) {
            if (table[x][y + 1] != null && subjects[z].equals(table[x][y + 1])) {
                return true;
            }
        }
        if (y == BREAK_1 + 1 || y == BREAK_2 + 1) {
            return table[x][y - 1] != null && subjects[z].equals(table[x][y - 1]);
        }
        return false;
    }

    public static void setDouble(String[][] table, int[] position) {
        int x = position[0];
        int y = position[1];
        int z = position[2];

        if (y == 0 || y == 2 || y == BREAK_1 + 1 || y == BREAK_2 + 1 || y == TIME_SLOTS - 2) {
            table[x][y + 1] = subjects[z];
        }

        if (y == 1 || y == BREAK_1 || y == BREAK_2 || y == TIME_SLOTS - 1) {
            table[x][y - 1] = subjects[z];
        }

    }

    public static void printTimetable(String[][] timetable) {
        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < TIME_SLOTS; j++) {
                System.out.print(timetable[i][j] + "  ");
                if (j == BREAK_1 || j == BREAK_2) {
                    System.out.print("|\t|");
                }
            }
            System.out.println("\n");
        }

    }


    public void assignTimeSlot2(Stream stream) {
        List<Lesson> lessons;
        List<Timeslot> timeslots;

        while (!(lessons = this.lessonsWithMoreTime(stream)).isEmpty() && !(timeslots = this.freeTimeslots(stream)).isEmpty()) {
            for (Lesson lesson : lessons) {
                int selectedTimeslot = rd.nextInt(timeslots.size());
                if (isOverLap(lesson, timeslots.get(selectedTimeslot))) {
                    continue;
                }
                // TODO:bidirectional
//                lesson.getTimeslots().add(timeslots.get(selectedTimeslot));

                timeslots.get(selectedTimeslot).getLessons().add(lesson);
                timeslotRepository.save(timeslots.get(selectedTimeslot));

                break;
            }
        }


    }

    private boolean isOverLap(Lesson lesson, Timeslot timeslot) {
        return timeslot.getLessons().stream()
                .anyMatch(
                        les -> les.getId().getTeacher() == lesson.getId().getTeacher()
                );
    }


    private boolean hasMoreHours(Lesson lesson) {
        List<Timeslot> timeslots = timeslotRepository.findAllByLessonsContaining(lesson);
        long sum = 0;
        for (Timeslot timeslot : timeslots) {
            sum = sum + timeslot.getId().getStartTime()
                    .until(timeslot.getId().getEndTime(), ChronoUnit.HOURS);
        }
        return sum < lesson.getHoursPerWeek();
    }

    private List<Lesson> lessonsWithMoreTime(Stream stream) {
        List<Lesson> allLessons = lessonRepository.findAllById_Stream(stream);
        return allLessons.stream().filter(this::hasMoreHours).toList();
    }


    private List<Timeslot> freeTimeslots(Stream stream) {
        List<Timeslot> timeslots = timeslotRepository.findAll();
        return timeslots.stream().filter(
                timeslot -> timeslot.getLessons().stream().noneMatch(
                        lesson -> lesson.getId().getStream() == stream
                )
        ).toList();

    }

}

