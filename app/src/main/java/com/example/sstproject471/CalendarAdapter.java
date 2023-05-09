package com.example.sstproject471;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<String> daysofMonth;

    // THIS IS BASICALLY OUR BUTTON THAT WILL CALL UP THE POP UP!! IT'S USED WHEN YOU CLICK A BOX ON CALENDAR.
    private final OnItemListener onItemListener;






    public CalendarAdapter(ArrayList<String> daysofMonth, OnItemListener onItemListener) {
        this.daysofMonth = daysofMonth;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.16666666);
        return new CalendarViewHolder(view, onItemListener);

    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayofMonth.setText(daysofMonth.get(position));
    }

    @Override
    public int getItemCount() {
        return daysofMonth.size();
    }


    // THIS IS AN INTERFACE CLASS JAROD MADE TO MAKE SURE THE DAY AND POSITION OF THE CALENDAR IS
    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }



}

