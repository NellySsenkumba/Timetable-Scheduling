package org.school.timetableschedulingsystem.timeslot;

import org.school.timetableschedulingsystem.models.database.Lesson;
import org.school.timetableschedulingsystem.models.database.Teacher;
import org.school.timetableschedulingsystem.models.database.Timeslot;
import org.school.timetableschedulingsystem.models.database.keys.TimeslotCompositePrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TimeslotRepository extends JpaRepository<Timeslot, TimeslotCompositePrimaryKey> {
    Optional<Timeslot> findAllById_DayAndId_StartTimeAndId_EndTime(DayOfWeek day, LocalTime startTime, LocalTime endTime);

    List<Timeslot> findAllByLessonsContaining(Lesson lesson);
    List<Timeslot> findAllByLessons_Id_Teacher(Teacher teacher);

     List<Timeslot> findByLessonsContaining(Lesson lesson);
}
