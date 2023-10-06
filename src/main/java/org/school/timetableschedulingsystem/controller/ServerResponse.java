package org.school.timetableschedulingsystem.controller;

public record ServerResponse(
        String status,
        Object data
) {
}
