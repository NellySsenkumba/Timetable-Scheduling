package org.school.timetableschedulingsystem.controller;

import lombok.Setter;

public abstract class ServiceHandler {

    @Setter
    private ServiceHandler serviceHandler;
    public Object handleRequest(Object request){
        return null;
    }

}
