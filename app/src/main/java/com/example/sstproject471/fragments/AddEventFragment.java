package com.example.sstproject471.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sstproject471.R;
import com.example.sstproject471.eventfunctionality.CustomEventFactory;
import com.example.sstproject471.eventfunctionality.Event;
import com.example.sstproject471.eventfunctionality.EventFactory_IF;
import com.example.sstproject471.eventfunctionality.ExamEventFactory;
import com.example.sstproject471.eventfunctionality.HomeworkEventFactory;
import com.example.sstproject471.eventfunctionality.LeisureEventFactory;
import com.example.sstproject471.eventfunctionality.QuizEventFactory;
import com.example.sstproject471.eventfunctionality.StudyEventFactory;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class AddEventFragment extends Fragment {

        EventFactory_IF factory;
        Event event;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_add_event, container, false);

            Spinner typeSelect = view.findViewById(R.id.eventType);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.eventType_options, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeSelect.setAdapter(adapter);

            view.findViewById(R.id.bSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = typeSelect.getSelectedItem().toString();
                    String name = ((EditText) view.findViewById(R.id.eventName)).getText().toString();
                    String date = ((EditText) view.findViewById(R.id.eventDate)).getText().toString();
                    String time = ((EditText) view.findViewById(R.id.eventTime)).getText().toString();
                    String association = ((EditText) view.findViewById(R.id.eventAssociation)).getText().toString();
                    String description = ((EditText) view.findViewById(R.id.eventDescription)).getText().toString();

                    switch (type) {
                        case "HOMEWORK":
                            factory = new HomeworkEventFactory();
                            break;
                        case "EXAM":
                            factory = new ExamEventFactory();
                            break;
                        case "LEISURE":
                            factory = new LeisureEventFactory();
                            break;
                        case "QUIZ":
                            factory = new QuizEventFactory();
                            break;
                        case "STUDY":
                            factory = new StudyEventFactory();
                            break;
                        case "CUSTOM":
                            factory = new CustomEventFactory();
                            break;
                    }

                    event = factory.createEvent(name, date, time, association, description);

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    long timestamp = System.currentTimeMillis() * 1000000;
                    String documentId = "eventID: " + timestamp;

                    Map<String, Object> e = new HashMap<>();
                    e.put("event", event);

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
                }
            });
            return view;
        }
    }
