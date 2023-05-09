package com.example.sstdatabase.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sstdatabase.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<String> {
ArrayList<String> list;
Context context;

    public ListViewAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.list_row, items);
        this.context = context;
        list = items;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_row,null);

            TextView number=convertView.findViewById(R.id.number);
            number.setText(position+1+".");

            TextView name=convertView.findViewById(R.id.classname);
            name.setText(list.get(position));

            ImageView addclass= convertView.findViewById(R.id.startclass);
            ImageView endclass=convertView.findViewById(R.id.endclass);
        addclass.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

         }
        });
        endclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        }
        return convertView;
    }
}
