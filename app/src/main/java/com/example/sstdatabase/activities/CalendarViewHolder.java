package com.example.sstdatabase.activities;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sstdatabase.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayofMonth;
    private final CalendarAdapter.OnItemListener onItemListener;



    // I'm assuming this is the constructor for JARED'S WORK!!!!
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayofMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;

        System.out.println("OH YEAH");
        itemView.setOnClickListener(this);
    }


    // THIS FUNCTION GETS THE POSITION OF THE BOX THAT WAS JUST CLICKED ON!!!
    @Override
    public void onClick(View view) {


        onItemListener.onItemClick(getAdapterPosition(),(String) dayofMonth.getText());

        //CHECKING TO SEE IF THE USER SELECT A BOX ON THE CALENDAR!!
        System.out.println("HELLO THERE!!!!");



        // I WANT TO LAUNCH AN ACTIVITY WINDOW HERE!!! BASICALLY ONCE THE USER CLICKS AN EVENT ON THE CALENDAR, WE WANT TO REDIRECT THEM TO ANOTHER ACTIVITY THAT SHOWS:
        // ALL THE EVENT OBJECTS THAT ARE ON THAT DAY.

        // WE MUST LAUNCH A POP UP RIGHT HERE!!!
        //Intent i = new Intent(getApplicationContext(), EventPopUpActivity.class);
        //startActivity(i);

        //1. Will first show the pop up with the preview of event titles and times.
        //EventViewProxy proxy = new EventViewProxy();

        //2. After the database is done pulling info, we can now call the event function with all the events and information of each event.
        //Intent intent = new Intent(); // put in the event you want to launch!!
    }


}

