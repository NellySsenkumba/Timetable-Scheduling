package org.school.timetableschedulingsystem.teacher;

import org.school.timetableschedulingsystem.models.database.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
