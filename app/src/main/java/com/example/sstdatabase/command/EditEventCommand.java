package com.example.sstdatabase.command;

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
import com.example.sstdatabase.eventFactories.Event;
import com.example.sstdatabase.eventFactories.EventFactory_IF;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditEventCommand extends AppCompatActivity {

    EventFactory_IF factory;
    Event event;
    String eventId = "4VQMgVSKMFMgLmoT2Hpr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // Retrieve the event details from Firestore and populate the fields
        retrieveEventDetails();

        findViewById(R.id.bSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated values from the fields
                Spinner typeSelect = findViewById(R.id.eventType);
                String type = typeSelect.getSelectedItem().toString();

                EditText nameText = findViewById(R.id.eventName);
                String name = nameText.getText().toString();

                EditText dateText = findViewById(R.id.eventDate);
                String date = dateText.getText().toString();

                // Time Picker
                TimePicker timePicker = findViewById(R.id.eventTime);
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String time = String.format("%02d:%02d", hour, minute);

                EditText associationText = findViewById(R.id.eventAssociation);
                String association = associationText.getText().toString();

                EditText descriptionText = findViewById(R.id.eventDescription);
                String description = descriptionText.getText().toString();

                // Update the event in Firestore
                updateEvent(type, name, date, time, association, description);
            }
        });
    }

    private void retrieveEventDetails() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference eventRef = db.collection("events").document(eventId);
        eventRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Event event = documentSnapshot.toObject(Event.class);
                    populateFields(event);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("dbfirebase", "Error retrieving event", e);
            }
        });
    }

    private void populateFields(Event event) {
        Spinner typeSelect = findViewById(R.id.eventType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                EditEventCommand.this,
                R.array.eventType_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSelect.setAdapter(adapter);
        //typeSelect.setSelection(getIndex(typeSelect, event.getType()));

        EditText nameText = findViewById(R.id.eventName);
        nameText.setText(event.getName());

        EditText dateText = findViewById(R.id.eventDate);
        dateText.setText(event.getDate());

        TimePicker timePicker = findViewById(R.id.eventTime);
        int hour = Integer.parseInt(event.getTime().split(":")[0]);
        int minute = Integer.parseInt(event.getTime().split(":")[1]);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

        EditText associationText = findViewById(R.id.eventAssociation);
        associationText.setText(event.getAssociation());

        EditText descriptionText = findViewById(R.id.eventDescription);
        descriptionText.setText(event.getDescription());
    }

        private int getIndex(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return 0;
    }

    private void updateEvent(String type, String name, String date, String time, String association, String description) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference eventRef = db.collection("events").document(eventId);

        Map<String, Object> updates = new HashMap<>();
        updates.put("type", type);
        updates.put("name", name);
        updates.put("date", date);
        updates.put("time", time);
        updates.put("association", association);
        updates.put("description", description);

        eventRef.update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("dbfirebase", "Event updated successfully");
                        Toast.makeText(EditEventCommand.this, "Event updated successfully", Toast.LENGTH_SHORT).show();
                        // Handle the success scenario
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("dbfirebase", "Error updating event", e);
                        Toast.makeText(EditEventCommand.this, "Error updating event", Toast.LENGTH_SHORT).show();
                        // Handle the failure scenario
                    }
                });
    }
}