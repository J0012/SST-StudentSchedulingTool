package com.example.sstproject471.eventfunctionality;

public interface Event {
    String getName();
    String getDate();
    String getTime();
    String getAssociation();
    String getDescription();
    EventType getType();
}
