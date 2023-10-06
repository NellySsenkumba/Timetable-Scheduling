package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.school.timetableschedulingsystem.models.enums.Role;


import java.util.Collection;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Teacher {
    private String username;
    private String password;
    private String role;

}