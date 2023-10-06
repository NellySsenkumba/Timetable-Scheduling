package org.school.timetableschedulingsystem.utils;

public class CustomUtils{

    public static long convertSubjectIdToLong(Object subjectId) {
        if (subjectId == null) {
            throw new IllegalArgumentException("Subject id cannot be null");
        }

        if (subjectId instanceof Long) {
            return (Long) subjectId;
        } else if (subjectId instanceof Integer) {
            return (long) (int) (Integer) subjectId;
        } else if (subjectId instanceof String) {
            try {
                return Long.parseLong((String) subjectId);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format for subject id");
            }
        } else {
            throw new IllegalArgumentException("Subject id must be a number");
        }
    }

}
