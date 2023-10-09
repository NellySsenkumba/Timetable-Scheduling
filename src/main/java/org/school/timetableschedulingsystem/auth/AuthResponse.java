package org.school.timetableschedulingsystem.auth;

public record AuthResponse(
        String status,
        String token
) {
    public AuthResponse( String token) {
        this("0", token);
    }
}
