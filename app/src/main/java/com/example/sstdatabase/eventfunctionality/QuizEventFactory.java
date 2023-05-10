package com.example.sstdatabase.eventfunctionality;

public class QuizEventFactory implements EventFactory_IF{
    @Override
    public Event createEvent(String name, String date, String time, String association, String description) {
        return new QuizEvent(name,date,time,association,description);
    }
}
