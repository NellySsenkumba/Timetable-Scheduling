package org.school.timetableschedulingsystem.handler;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.controller.ClientRequest;
import org.school.timetableschedulingsystem.dashboard.DashboardService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardRequestHandler extends RequestHandler{
    private final DashboardService  dashboardService;
    @Override
    Object handleRequest(ClientRequest clientRequest) {
        return switch (clientRequest.action()){
            case "dashboard" ->dashboardService.show(clientRequest.data());
            case "teachers-per-class" ->dashboardService.numberOfTeachersPerClass();
            default -> nextRequestHandler.handleRequest(clientRequest);
        };
    }
}
