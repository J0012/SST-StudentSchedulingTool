package com.example.sstdatabase.eventfunctionality;

public class CustomEventFactory implements EventFactory_IF{
    @Override
    public Event createEvent(String name, String date, String time, String association, String description) {
        return new CustomEvent(name,date,time,association,description);
    }
}
