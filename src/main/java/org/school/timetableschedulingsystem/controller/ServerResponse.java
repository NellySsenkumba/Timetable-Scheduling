package org.school.timetableschedulingsystem.controller;

public record ServerResponse<T>(
        String status,
        T data

) {
    public ServerResponse(T data) {
        this("0", data);
    }
}
