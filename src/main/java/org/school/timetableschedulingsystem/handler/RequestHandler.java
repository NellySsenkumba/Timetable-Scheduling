package org.school.timetableschedulingsystem.handler;


import org.school.timetableschedulingsystem.controller.ClientRequest;

public abstract class RequestHandler {
    protected RequestHandler nextRequestHandler;

    abstract Object handleRequest(ClientRequest clientRequest);

    void setNextRequestHandler(RequestHandler nextRequestHandler) {
        this.nextRequestHandler = nextRequestHandler;
    }
}
