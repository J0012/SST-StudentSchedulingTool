package com.example.sstdatabase.eventfunctionality;

public class LeisureEventFactory implements EventFactory_IF{
    @Override
    public Event createEvent(String name, String date, String time, String association, String description) {
        return new LeisureEvent(name,date,time,association,description);
    }
}
