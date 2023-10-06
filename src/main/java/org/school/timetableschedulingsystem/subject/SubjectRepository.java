package org.school.timetableschedulingsystem.subject;

import org.school.timetableschedulingsystem.models.database.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
