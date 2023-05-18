package com.example.sstdatabase.proxy;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sstdatabase.R;

import java.util.ArrayList;


// ******************** THIS CLASS WILL DISPLAY THE POP UP MESSAGE TO THE USER!! BUT WE GOT TO MAKE SURE WE DO IT ON THE CALENDAR VIEW HOLDER PAGE!!

public class EventViewProxy extends AppCompatActivity implements EventView_IF {


    // EACH EVENT OBJECT FROM THE PROXY HAS ONLY 2 ATTRIBUTES
    private String eventTitle;
    private String eventTimeInterval;
    private ArrayList<String> event = new ArrayList<String>();




    // CONSTRUCTOR FOR THE PROXY EVENT CREATOR
    public EventViewProxy(ArrayList<String> e){
        this.event = e;
        setEventTitle();
        setEventTimeInterval();
    }

    public EventViewProxy() {
        // No args constructor
    }


    // IT'S FROM THE OTHER CLASS RETURN TYPE!!!
    // THIS FUNCTION WILL COMPLETE THE EVENT!!! WE WANT TO FILL IN THE EVENT WITH MORE DETAILS TO MAKE IT COMPLETE!!
    public EventView complete(){

        System.out.println("Pulling the rest of Event 1's details from the Database...");
        // These values will be fetched from a DATABASE IN THE ACTUAL IMPLEMENTATION
        event.add("May");
        event.add("14");
        event.add("2023");
        event.add("I need to do some math homework right before this appointment so that I won't have to worry about it after work.");
        event.add("CUSTOM");

        System.out.println("Full Event Was Created!");
        return new EventView(event);
    }



    // Declare the button private members ***************************************************** This section brings up the pop up
    Button btn_goBack;
    Button btn_goToFullEventPage;


    // Start: THESE ARE USED TO POPULATE THE ARRAYLIST ON THE UI PAGES
    ArrayList<String> publicList;
    ListView eventPopUpListView;
    // end



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pop_up);

        //In order to use the ArrayAdapter, we must have the above line code present.
        publicList = new ArrayList<>();
        publicList.add("Dentist Appointment\n12:00pm");
        //publicList.add("Do Homework\n3:00pm-4:00pm");



        eventPopUpListView = (ListView)findViewById(R.id.eventlistview); //search for the id of the ListView on the UI page!!!

        //if(eventPopUpListView == null) {System.out.println("It's NULL!!!!");} // when null, it crashes, have to have the id of the ListView stored before it works!
        //else{System.out.println("NOT NULL" + eventPopUpListView);}

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.select_dialog_item, publicList);

        eventPopUpListView.setAdapter(arrayAdapter);



        // Because we want this activity to pop up we will use the displayMetrics function!
        DisplayMetrics dmPop = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dmPop);

        // now we want to set height and width of the pop up based on dmPop
        int width = dmPop.widthPixels;
        int height = dmPop.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.7));


        // THIS GETS RID OF THE BLACK BACKGROUND OUTSIDE OF THE POP UP!!!
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        // AFTER THAT, WE HEAD TO STYLES.XML AND THEN ADD THE STYLE name = "AppTheme.PopMe
        // there we will set up how the pop up closes and also where the pop up background is translucent.



        // THIS IS WHERE WE HAVE BUTTON LISTENERS!!
        btn_goBack =(Button) findViewById(R.id.button_cancel);
        btn_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // THIS FUNCTION CLOSES AN ACTIVITY!! We close the pop up activity!
            }
        });

        btn_goToFullEventPage = (Button) findViewById(R.id.button_continue); // name of button on .xml
        btn_goToFullEventPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. want to close pop up
                finish();
                // 2. Now we want to launch the page that has all the event details on it!
                //Intent intent = new Intent(getApplicationContext(), fullEventDetails.class);
                Intent intent = new Intent(getApplicationContext(), EventView.class);
                startActivity(intent);

            }
        });

        // ********************************************************************************************** End of pop up section!!
    }






    // THESE 3 FUNCTIONS ARE IMPLEMENTED FROM THE INTERFACE

    @Override
    public ArrayList<String> bringList(ArrayList<String> events) {
        return event;
    }


    // DISPLAYS A POP UP ONTO THE CalendarViewHolder class, thing is, this function is only used to verify that we are adding stuff to the screen
    public void displayEventInfo() {

        // Variables to store the event details!
        String title;
        String time;


        title = getEventTitle(); // I want to call the getEventTitle function and give the string object and get it's title.
        time = getEventTime();


        System.out.println("..:: PROXY EVENT ::..");
        System.out.println("This is the Title of Event: "  + title); // Want to print out the event details to the popup.
        System.out.println("This is the Time Interval of Event: " + time); // Want to print out the event details to the popup.

    }

    // SETTERS!!
    private void setEventTitle() {
        this.eventTitle = event.get(0);
    }
    private void setEventTimeInterval() {
        this.eventTimeInterval = event.get(1);
    }


    // GETTERS
    @Override
    public String getEventTitle() {
        return eventTitle; //event.get(1).toString();
    }
    @Override
    public String getEventTime() {
        return eventTimeInterval; //event.get(2).toString();
    }


}

