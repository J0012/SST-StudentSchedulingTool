package com.example.sstdatabase.eventFactories;

public class StudyEventFactory implements EventFactory_IF{
    @Override
    public Event createEvent(String name, String date, String time, String association, String description) {
        return new StudyEvent(name,date,time,association,description);
    }
}
