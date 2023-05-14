package com.example.sstdatabase.activities;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sstdatabase.R;

import java.util.ArrayList;


// THIS CLASS WILL DISPLAY THE FULL PAGE OF EVENTS TO THE UI.
// THATS WHY WE HAVE THE ARRAY HERE!!
public class EventView extends AppCompatActivity implements EventView_IF{

    private String eventTitle;
    private String eventTimeInterval;
    private String eventMonth;
    private String eventDay;
    private String eventYear;
    private String eventDescription;
    private String eventAssociation;

    // THIS IS THE PRIVATE EVENT OBJECT I WANT TO USE TO STORE DATA INTO THE OBJECTS!!!
    private ArrayList<String> event;//= new ArrayList<String>();

    //private EventView_IF eve;

    // CONSTRUCTOR FUNCTION
    // parameter accepts a string arraylist that has one event types info.
    public EventView(ArrayList<String> e){
        this.event = e;
        setEventTitle();
        setEventTimeInterval();
        setEventMonth();
        setEventDay();
        setEventYear();
        setEventDescription();
        setEventAssociation();
    }


    // Start: THESE ARE USED TO POPULATE THE ARRAYLIST ON THE UI PAGE, THE activity_full_event_details page ***********************
    public EventView() {
        // No args constructor. Default constructor needed at some point!!
    }


    ArrayList<String> publicList;
    ListView eventPopUpListView;
    // end



    // This acts as our client!!! It's on this class yes, but you must act as it doesn't belong to it. That way you don't get confused with it.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_event_details);


        //In order to use the ArrayAdapter, we must have the above line code present.
        publicList = new ArrayList<>(); //this line prevents us from adding elements to a null object!


        //  THIS SECTION WILL NEED TO CALL A DATABASE INSTANCE TO POPULATE THE ARRAY WITH THE TIME AND DATE
        publicList.add("Dentist Appointment");
        publicList.add("12:00pm-1:00pm");


        // I want to call a proxy incomplete event and show the info.
        //(later: Now I want to see the list of events, but in the proxy pop up.)
        EventView_IF eventProxy = new EventViewProxy(publicList);
        eventProxy.displayEventInfo();


        System.out.println("Step 2: Create Complete Event (used when user clicks 'Show more!' button)");
        // I now want to call a complete event and show the info.
        eventProxy = ((EventViewProxy)eventProxy).complete(); // this will create a new interface object list.
        eventProxy.displayEventInfo();


        //publicList.add("Other\nDentist Appointment\n12:00pm-1:00pm\nMay 14 2023\n\nThis needs to be done before I go to class.");
        //publicList.add("Homework\nDo Homework Assignment\n3:00pm-4:00pm\nMay 14 2023\n\nI have to finish all my homework before the evening soccer game.");


        eventPopUpListView = (ListView)findViewById(R.id.fullEventDetailsListView); //search for the id of the ListView on the UI page!!!

        if(eventPopUpListView == null) {System.out.println("It's NULL!!!!");} // when null, it crashes, have to have the id of the ListView stored before it works!
        else{System.out.println("NOT NULL" + eventPopUpListView);}

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.select_dialog_item, publicList); //,publicList) this is where we put our event

        eventPopUpListView.setAdapter(arrayAdapter);
    }
    // ******************************************************************************************* end of populating event page section






    // THESE 3 FUNCTIONS ARE IMPLEMENTED FROM THE INTERFACE!!!

    @Override
    public ArrayList<String> bringList(ArrayList<String> events) {
        return event;
    }

    // DISPLAY INFORMATION FROM THE EVENT ONTO THE POPUP on THE CalendarViewHolder class
    @Override
    public void displayEventInfo() {

        // Variables to store the returned values
        String title, time, month, day, year, description, association;


        System.out.println("..:: COMPLETE EVENT ::..");
        title = getEventTitle(); // I want to call the getEventTitle function and give the string object and get it's title.
        System.out.println("Title of event: " + title); // Want to print out the event details to the popup.
        time = getEventTime();
        System.out.println("Time frame of event: " + time); // Want to print out the event details to the popup.
        month = getEventMonth();
        System.out.println("Month of Event: " + month);
        day = getEventDay();
        System.out.println("Day of event: " + day);
        year = getEventYear();
        System.out.println("Year of event: " + year);
        description = getEventDescription();
        System.out.println("Description of event: " + description);
        association = getEventAssociation();
        System.out.println("Association of event: " + association);


    }


    // May need a function to combine month, day, and year

    // SETTERS!!
    private void setEventTitle() {
        this.eventTitle = event.get(0);
    }
    private void setEventTimeInterval() {
        this.eventTimeInterval = event.get(1);
    }
    private void setEventMonth(){
        this.eventMonth = event.get(2);
    }
    private void setEventDay(){
        this.eventDay = event.get(3);
    }
    private void setEventYear(){
        this.eventYear = event.get(4);
    }
    private void setEventDescription(){
        this.eventDescription = event.get(5);
    }
    private void setEventAssociation(){
        this.eventAssociation = event.get(6);
    }



    // GETTERS!!!
    @Override
    public String getEventTitle() {
        return eventTitle;
    }
    @Override
    public String getEventTime() {
        return eventTimeInterval;
    }
    public String getEventMonth() {
        return eventMonth;
    }
    public String getEventDay() {
        return eventDay;
    }
    public String getEventYear() {
        return eventYear;
    }
    public String getEventDescription() {
        return eventDescription;
    }
    public String getEventAssociation() {
        return eventAssociation;
    }
}
