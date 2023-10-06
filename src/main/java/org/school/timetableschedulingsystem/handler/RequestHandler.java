package org.school.timetableschedulingsystem.handler;


import org.school.timetableschedulingsystem.controller.ClientRequest;

import java.util.Map;

public abstract class ServiceHandler {
    protected ServiceHandler nextServiceHandler;
    abstract Object handleRequest(ClientRequest clientRequest);

    void setNextServiceHandler(ServiceHandler nextServiceHandler){
        this.nextServiceHandler=nextServiceHandler;
    }
}
