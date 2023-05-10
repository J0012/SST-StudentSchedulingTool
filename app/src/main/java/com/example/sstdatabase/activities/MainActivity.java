package com.example.sstdatabase.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sstdatabase.R;
import com.example.sstdatabase.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity { // implements CalendarAdapter.OnItemListener
//    private AppBarConfiguration appBarConfiguration;
//        private ActivityMainBinding binding;
//
//        private TextView monthYearText;
//        private RecyclerView calenderRecyclerView;
//        private LocalDate selectedDate;
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//
//            binding = ActivityMainBinding.inflate(getLayoutInflater());
//            setContentView(binding.getRoot());
//
//            setSupportActionBar(binding.toolbar);
//            initwidgets();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                selectedDate = LocalDate.now();
//            }
//            setMonthView();
//            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//            binding.fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });
//        }
//
//        private void setMonthView() {
//            monthYearText.setText(monthYearFromDate(selectedDate));
//            ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
//
//            CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
//            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
//            calenderRecyclerView.setLayoutManager(layoutManager);
//            calenderRecyclerView.setAdapter(calendarAdapter);
//        }
//
//        private ArrayList<String> daysInMonthArray(LocalDate date) {
//            ArrayList<String> daysInMonthArray = new ArrayList<>();
//            YearMonth yearMonth = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                yearMonth = YearMonth.from(date);
//            }
//            int daysInMonth = 0;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                daysInMonth = yearMonth.lengthOfMonth();
//            }
//
//            LocalDate firstOfMonth = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                firstOfMonth = selectedDate.withDayOfMonth(1);
//            }
//            int dayOfWeek = 0;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
//            }
//
//            for(int i = 1; i<=42; i++)
//            {
//                if(i<=dayOfWeek || i>daysInMonth + dayOfWeek)
//                    daysInMonthArray.add("");
//                else{
//                    daysInMonthArray.add(String.valueOf(i - dayOfWeek));
//                }
//            }
//            return daysInMonthArray;
//
//
//        }
//
//        private String monthYearFromDate(LocalDate date){
//            DateTimeFormatter formatter = null;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
//            }
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                return date.format(formatter);
//            }
//            return null;
//        }
//
//        private void initwidgets() {
//            calenderRecyclerView = findViewById(R.id.calendarRecyclerView);
//            monthYearText = findViewById(R.id.monthYearTV);
//        }
//
//        public void previousMonthAction(View view){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                selectedDate = selectedDate.minusMonths(1);
//            }
//            setMonthView();
//        }
//
//        public void nextMonthAction(View view){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                selectedDate = selectedDate.plusMonths(1);
//            }
//            setMonthView();
//        }
//
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            getMenuInflater().inflate(R.menu.menu_main, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            // Handle action bar item clicks here. The action bar will
//            // automatically handle clicks on the Home/Up button, so long
//            // as you specify a parent activity in AndroidManifest.xml.
//            int id = item.getItemId();
//
//            //noinspection SimplifiableIfStatement
//            if (id == R.id.action_settings) {
//                return true;
//            }
//
//            return super.onOptionsItemSelected(item);
//        }
//
//        @Override
//        public boolean onSupportNavigateUp() {
//            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//            return NavigationUI.navigateUp(navController, appBarConfiguration)
//                    || super.onSupportNavigateUp();
//        }
//
//        @Override
//        public void onItemClick(int position, String dayText) {
//            String message = "Selected Date: " + dayText + " " + monthYearFromDate(selectedDate);
//            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//        }
//
//
//    }


    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}