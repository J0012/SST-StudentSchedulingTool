package com.example.sstdatabase.eventfunctionality;

public interface EventFactory_IF {
    Event createEvent(String name,String date,String time, String association, String description);
}
