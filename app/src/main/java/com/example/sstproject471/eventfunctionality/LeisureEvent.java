package com.example.sstproject471.eventfunctionality;

public class LeisureEvent implements Event{
    private String name;
    private String date;
    private String time;
    private String association;
    private String description;

    public LeisureEvent(String name, String date, String time, String association, String description) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.association = association;
        this.description = description;
    }
    @Override
    public EventType getType(){
        return EventType.LEISURE;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

