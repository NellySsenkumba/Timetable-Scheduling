package org.school.timetableschedulingsystem.dashboard.dto;

import java.util.List;

public record DashboardResponse(
        int totalNumberOfTeacher,
        int totalNumberOfSubjects,
        int totalNumberOfLessons,
        int totalNumberOfStreams,
        List<DashboardClassTeacherResponse> classTeachers
        ) {
}
