package org.school.timetableschedulingsystem.lesson.repository;

import org.school.timetableschedulingsystem.models.database.Lesson;
import org.school.timetableschedulingsystem.models.database.Stream;
import org.school.timetableschedulingsystem.models.database.Teacher;
import org.school.timetableschedulingsystem.models.database.Timeslot;
import org.school.timetableschedulingsystem.models.database.keys.LessonCompositePrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, LessonCompositePrimaryKey> {
    List<Lesson> findAllById_Teacher(Teacher teacher);

    List<Lesson> findAllById_Stream(Stream stream);
    List<Lesson> findAllById_Stream_ClassRoom(String  classroom);

}
