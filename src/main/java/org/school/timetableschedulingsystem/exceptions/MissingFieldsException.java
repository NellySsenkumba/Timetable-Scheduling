package org.school.timetableschedulingsystem.exceptions;

public class MissingFieldsException extends RuntimeException{
    public MissingFieldsException(){
        super("missing fields");
    }
}
