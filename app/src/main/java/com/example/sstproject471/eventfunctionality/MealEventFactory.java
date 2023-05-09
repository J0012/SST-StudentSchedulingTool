package com.example.sstproject471.eventfunctionality;

public class MealEventFactory implements EventFactory_IF{
    @Override
    public Event createEvent(String name, String date, String time, String association, String description) {
        return new MealEvent(name,date,time,association,description);
    }

}
