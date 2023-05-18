//package com.example.sstdatabase.decorator;
//
//import static io.grpc.internal.SharedResourceHolder.holder;
//
//import android.util.Log;
//import android.view.View;
//import android.content.Intent;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//public class EventDecorator implements EventDecorator_IF {
//
//
//    @Override
//    public void createDot() {
//        db.collection("events")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document : task.getResult()) {
//                                holder.eventDot.setVisibility(View.VISIBLE);
//                                Log.d("dbfirebase", "retrieved: " + " ID: " + document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w("dbfirebase", "Error getting documents.", task.getException());
//                        }
//                    }
//                });
//    }
//
//}
//
//}
