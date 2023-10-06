package org.school.timetableschedulingsystem.controller;

import org.springframework.lang.NonNull;

import java.util.Map;

public record ClientRequest(
        @NonNull String action,

        Map<String ,Object> data
) {
}
