package com.example.sstproject471.eventfunctionality;

public class ExamEventFactory implements EventFactory_IF{
    @Override
    public Event createEvent(String name, String date, String time, String association, String description) {
        return new ExamEvent(name,date,time,association,description);
    }
}
