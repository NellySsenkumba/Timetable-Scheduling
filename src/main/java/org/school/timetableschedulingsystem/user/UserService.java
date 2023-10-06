package org.school.timetableschedulingsystem.user;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.models.database.Teacher;
import org.school.timetableschedulingsystem.models.database.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User addUser(Map<String, Object> data) {


        User user = new User((String) data.get("username"),
                data.get("password").toString(),
                data.get("role").toString());

        System.out.println(data.get("who"));
        System.out.println((Collection)data.get("who"));
        System.out.println(data.getOrDefault("them","us"));


        return userRepository.saveAndFlush(user);
    }
}
