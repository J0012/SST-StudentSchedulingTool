package com.example.sstdatabase.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sstdatabase.R;
import com.example.sstdatabase.eventfunctionality.CustomEventFactory;
import com.example.sstdatabase.eventfunctionality.Event;
import com.example.sstdatabase.eventfunctionality.EventFactory_IF;
import com.example.sstdatabase.eventfunctionality.ExamEventFactory;
import com.example.sstdatabase.eventfunctionality.HomeworkEventFactory;
import com.example.sstdatabase.eventfunctionality.LeisureEventFactory;
import com.example.sstdatabase.eventfunctionality.QuizEventFactory;
import com.example.sstdatabase.eventfunctionality.StudyEventFactory;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AddEventActivity extends AppCompatActivity {
    EventFactory_IF factory;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_page);
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

                EditText timeText = findViewById(R.id.eventTime);
                String time = timeText.getText().toString();

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

                FirebaseFirestore db = FirebaseFirestore.getInstance();

//            // Get the current timestamp in milliseconds
//            long timestamp = System.currentTimeMillis() * 1000000;
//
//            // Use the timestamp as the document ID
//            String documentId = "eventID: " + timestamp;

                Map<String, Object> e = new HashMap<>();
                e.put("event", event);
//            e.put("type", type);
//            e.put("name", name);
//            e.put("date", date);
//            e.put("time", time);
//            e.put("association", association);
//            e.put("description", description);
//
//                //Giving event an easy-to-recognize ID
//                db.collection("events").document(documentId).set(event);

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

                String eventDetails = "Event Type: " + event.getType() +
                        "\nEvent Name: " + event.getName() +
                        "\nDate: " + event.getDate() +
                        "\nTime: " + event.getTime() +
                        "\nAssociated Class: " + event.getAssociation() +
                        "\nDescription: " + event.getDescription();

                Toast.makeText(AddEventActivity.this,"Event added successfully!\n" + eventDetails, Toast.LENGTH_LONG).show();


                //Retrival Portion
                db.collection("events")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot document : task.getResult()) {
                                        Log.d("dbfirebase", "retrieved: " + " ID: " + document.getId() + " => " + document.getData());
                                        String docDetails = "Event Type: " + document.getData();
                                        Toast.makeText(AddEventActivity.this,"Event added successfully!\n" + docDetails, Toast.LENGTH_LONG).show();

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
