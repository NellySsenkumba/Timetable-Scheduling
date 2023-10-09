package org.school.timetableschedulingsystem.controller;

public record ServerResponse(
        String status,
        Object data
) {
    public ServerResponse(Object data) {
        this("0", data);
    }
}
