package org.school.timetableschedulingsystem.user;

import org.school.timetableschedulingsystem.models.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
