package org.school.timetableschedulingsystem.auth;

public record AuthRequest(
        String username,
        String password
) {
}
