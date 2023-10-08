package org.school.timetableschedulingsystem.models.database;

import jakarta.persistence.*;
import lombok.Data;
import org.school.timetableschedulingsystem.models.enums.Permissions;

import java.util.Collection;
import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    private Long id;
    private String code;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Set<Permissions> permissions;
    @OneToMany(mappedBy = "role")
    private Collection<User> users;
}
