package com.example.sstdatabase.eventFactories;

public interface EventFactory_IF {
    Event createEvent(String name,String date,String time, String association, String description);
}