package com.example.sstproject471.eventfunctionality;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sstproject471.R;

public class EventPage extends AppCompatActivity {

    String type, name, date, time, association, description;
    EventFactory_IF factory;
    Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        TextView eventtype = (TextView) findViewById(R.id.eventtype);
        TextView eventname = (TextView) findViewById(R.id.eventname);
        TextView eventdate = (TextView) findViewById(R.id.eventdate);
        TextView eventtime = (TextView) findViewById(R.id.eventtime);
        TextView eventassociation = (TextView) findViewById(R.id.eventassociation);
        TextView eventdescription = (TextView) findViewById(R.id.eventdescription);

        Button eventButton=(Button) findViewById(R.id.eventbutton);
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = eventtype.getText().toString();
                name = eventname.getText().toString();
                date = eventdate.getText().toString();
                time = eventtime.getText().toString();
                association = eventassociation.getText().toString();
                description = eventdescription.getText().toString();

                switch(type){
                    case "HOMEWORK":
                        factory=new HomeworkEventFactory();
                        break;
                    case "EXAM":
                        factory=new ExamEventFactory();
                        break;
                    case "LEISURE":
                        factory=new LeisureEventFactory();
                        break;
                    case "QUIZ":
                        factory=new QuizEventFactory();
                        break;
                    case "STUDY":
                        factory=new StudyEventFactory();
                        break;
                    case "CUSTOM":
                        factory=new CustomEventFactory();
                        break;
                }
                event = factory.createEvent(name,date,time,association,description);

                String eventDetails = "Event Type: " + event.getType() +
                        "\nEvent Name: " + event.getName() +
                        "\nDate: " + event.getDate() +
                        "\nTime: " + event.getTime() +
                        "\nAssociated Class: " + event.getAssociation() +
                        "\nDescription: " + event.getDescription();

                Toast.makeText(EventPage.this,"Event added successfully!\n" + eventDetails, Toast.LENGTH_LONG).show();
            }
        });
    }




}