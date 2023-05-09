package com.example.sstproject471;


import java.util.ArrayList;

// THIS CLASS WILL HOLD THE FUNCTIONS THAT THE MAIN CLASS WILL CALL FROM THE CALENDARVIEW PROXY CLASS AND THEN THE CALENDARVIEW
public interface EventView_IF {


    //public ArrayList<String> event = new ArrayList<>();
    public ArrayList<String> bringList(ArrayList<String> events); // returns the list we want!!

    // This will display the event info as a string to the user. Either to the pop up or to the event activity.
    public void displayEventInfo();

    // This will get the EventTitle of the event object from the database. Either to the pop up or to the event activity.
    public String getEventTitle();

    // This function will get the time of the event object from the database. Either to the pop up or to the event activity.
    public String getEventTime();


}
