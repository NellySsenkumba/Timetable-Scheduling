package org.school.timetableschedulingsystem.stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.school.timetableschedulingsystem.models.database.Stream;

@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {

}
