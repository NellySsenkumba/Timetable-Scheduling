package org.school.timetableschedulingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.handler.TeacherRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/")
@RequiredArgsConstructor
public class EntryPoint {
    private final TeacherRequestHandler teacherServiceHandler;

    @RequestMapping
    public ResponseEntity<ServerResponse> getRequest(@RequestBody @NonNull ClientRequest request) {
        ServerResponse response = new ServerResponse(
                "0",
                teacherServiceHandler.handleRequest(request)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
