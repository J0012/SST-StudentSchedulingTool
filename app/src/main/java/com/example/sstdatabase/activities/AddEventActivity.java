package com.example.sstdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sstdatabase.R;
import com.example.sstdatabase.eventFactories.CustomEventFactory;
import com.example.sstdatabase.eventFactories.Event;
import com.example.sstdatabase.eventFactories.EventFactory_IF;
import com.example.sstdatabase.eventFactories.ExamEventFactory;
import com.example.sstdatabase.eventFactories.HomeworkEventFactory;
import com.example.sstdatabase.eventFactories.LeisureEventFactory;
import com.example.sstdatabase.eventFactories.QuizEventFactory;
import com.example.sstdatabase.eventFactories.StudyEventFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AddEventActivity extends AppCompatActivity {
        EventFactory_IF factory;
        Event event;
        FloatingActionButton calButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_event);

            calButton=(FloatingActionButton) findViewById(R.id.calendarButton); //we must make a button finder and it will find the calendar button id we make for "View Calendar" button in our main activity
            calButton.setOnClickListener(new View.OnClickListener() { //we made a button listener function
                @Override
                public void onClick(View view) {
                    //openActivity2(); //we call the function openActivity2 to do the action of opening activity2(which is a new page for viewing the calendar)
                    Intent i = new Intent(AddEventActivity.this, CalendarLauncher.class);
                    startActivity(i);
                }
            });


            findViewById(R.id.bSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Spinner typeSelect = findViewById(R.id.eventType);
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddEventActivity.this, R.array.eventType_options, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    typeSelect.setAdapter(adapter);
                    String type = typeSelect.getSelectedItem().toString();

                    EditText nameText = findViewById(R.id.eventName);
                    String name = nameText.getText().toString();

                    EditText dateText = findViewById(R.id.eventDate);
                    String date = dateText.getText().toString();

                    //Time Picker
                    TimePicker timePicker = findViewById(R.id.eventTime);

                    int hour = timePicker.getCurrentHour();
                    int minute = timePicker.getCurrentMinute();

                    String time = String.format("%02d:%02d", hour, minute);

                    // Use the selectedTime string as needed
                    System.out.println("Selected time: " + time);

                    EditText associationText = findViewById(R.id.eventAssociation);
                    String association = associationText.getText().toString();

                    EditText descriptionText = findViewById(R.id.eventDescription);
                    String description = descriptionText.getText().toString();

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




                    Map<String, Object> e = new HashMap<>();
                    e.put("event", event);

                    String eventDetails = "Event Type: " + event.getType() +
                            "\nEvent Name: " + event.getName() +
                            "\nDate: " + event.getDate() +
                            "\nTime: " + event.getTime() +
                            "\nAssociated Class: " + event.getAssociation() +
                            "\nDescription: " + event.getDescription();

                    Toast.makeText(AddEventActivity.this,"Event added successfully!\n" + eventDetails, Toast.LENGTH_LONG).show();


                    //Creates instance of Firebase
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    //Adding event to db
                    db.collection("events")
                            .add(e)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("dbfirebase", "saved: " + e);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Log.w("dbfirebase Failed", "Error adding document", exception);
                                }
                            });


                    //Retrieval Portion
                    db.collection("events")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (DocumentSnapshot document : task.getResult()) {
                                            Log.d("dbfirebase", "retrieved: " + " ID: " + document.getId() + " => " + document.getData());
//                                            String docDetails = "Event Type: " + document.getData();
//                                            Toast.makeText(AddEventPage.this,"Event added successfully!\n" + docDetails, Toast.LENGTH_LONG).show();

                                        }
                                    } else {
                                        Log.w("dbfirebase", "Error getting documents.", task.getException());
                                    }
                                }
                            });


                }

            });
        }

    }
