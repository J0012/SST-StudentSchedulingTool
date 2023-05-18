package com.example.sstdatabase.eventFactories;

public interface Event {
    String getName();
    String getDate();
    String getTime();
    String getAssociation();
    String getDescription();
    EventType getType();
}
