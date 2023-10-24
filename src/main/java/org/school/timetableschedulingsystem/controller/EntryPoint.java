package org.school.timetableschedulingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.handler.TeacherRequestHandler;
import org.school.timetableschedulingsystem.scheduler.TimetableGenerator;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/")
@RequiredArgsConstructor
public class EntryPoint {
    private final TeacherRequestHandler teacherServiceHandler;
    private final TimetableGenerator timetableGenerator;

    @RequestMapping
    public ResponseEntity<ServerResponse> getRequest(@RequestBody @NonNull ClientRequest request) {
        ServerResponse response = new ServerResponse(
                teacherServiceHandler.handleRequest(request)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping("generate-pdf")
    public ResponseEntity<byte[]> generatePdf() {
        var bytes = timetableGenerator.generatePdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("timetable.pdf")
                .build());
        return new ResponseEntity<>(bytes.toByteArray(), headers, HttpStatus.OK);
    }

}
