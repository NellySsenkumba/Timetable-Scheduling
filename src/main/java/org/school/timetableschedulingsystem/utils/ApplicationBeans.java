package org.school.timetableschedulingsystem.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeans {
    @Bean
    public DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
}
