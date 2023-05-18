package com.example.sstdatabase.command;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sstdatabase.R;
import com.example.sstdatabase.activities.AddEventActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteEventCommand extends AppCompatActivity {
    String eventId = "4VQMgVSKMFMgLmoT2Hpr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.deleteFab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDocument();
                Toast.makeText(DeleteEventCommand.this,"Event added successfully!\n" , Toast.LENGTH_LONG).show();

            }

            public void deleteDocument() {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("events").document(eventId);

                docRef.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("dbfirebase", "Document deleted successfully");
                                // Handle the success scenario
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("dbfirebase", "Error deleting document", e);
                                // Handle the failure scenario
                            }
                        });
            }
        });
    }
}
