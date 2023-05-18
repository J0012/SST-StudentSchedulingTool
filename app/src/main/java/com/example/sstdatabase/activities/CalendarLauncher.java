package com.example.sstdatabase.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sstdatabase.R;
import com.example.sstdatabase.databinding.ActivityCalendarLauncherBinding;
import com.example.sstdatabase.proxy.EventViewProxy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class CalendarLauncher extends AppCompatActivity implements CalendarAdapter.OnItemListener{

        FloatingActionButton fab;
        private AppBarConfiguration appBarConfiguration;
        private ActivityCalendarLauncherBinding binding;

        private TextView monthYearText;
        private RecyclerView calenderRecyclerView;
        private LocalDate selectedDate;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityCalendarLauncherBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            setSupportActionBar(binding.toolbar);
            initwidgets();
            selectedDate = LocalDate.now();
            setMonthView();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


            fab = (FloatingActionButton) findViewById(R.id.floatingActionButton); //we must make a button finder and it will find the calendar button id we make for "View Calendar" button in our main activity
            fab.setOnClickListener(new View.OnClickListener() { //we made a button listener function
                @Override
                public void onClick(View view) {
                    //openActivity2(); //we call the function openActivity2 to do the action of opening activity2(which is a new page for viewing the calendar)
                    Intent i = new Intent(CalendarLauncher.this, HomePageActivity.class);
                    startActivity(i);
                }
            });
        }

        private void setMonthView() {
            monthYearText.setText(monthYearFromDate(selectedDate));
            ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

            CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
            calenderRecyclerView.setLayoutManager(layoutManager);
            calenderRecyclerView.setAdapter(calendarAdapter);
        }

        private ArrayList<String> daysInMonthArray(LocalDate date) {
            ArrayList<String> daysInMonthArray = new ArrayList<>();
            YearMonth yearMonth = YearMonth.from(date);
            int daysInMonth = yearMonth.lengthOfMonth();

            LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
            int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

            for(int i = 1; i<=42; i++)
            {
                if(i<=dayOfWeek || i>daysInMonth + dayOfWeek)
                    daysInMonthArray.add("");
                else{
                    daysInMonthArray.add(String.valueOf(i - dayOfWeek));
                }
            }
            return daysInMonthArray;


        }

        private String monthYearFromDate(LocalDate date){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");

            return date.format(formatter);
        }

        private void initwidgets() {
            calenderRecyclerView = findViewById(R.id.calendarRecyclerView);
            monthYearText = findViewById(R.id.monthYearTV);
        }

        public void previousMonthAction(View view){
            selectedDate = selectedDate.minusMonths(1);
            setMonthView();
        }

        public void nextMonthAction(View view){
            selectedDate = selectedDate.plusMonths(1);
            setMonthView();
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

    @Override
    public void onItemClick(int position, String dayText) {

        //if(dayText.equals("")){
        String message = "Selected Date: " + dayText + " " + monthYearFromDate(selectedDate);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        //}


        // WE LAUNCH THE POP UP RIGHT HERE!!! //WILL NEED TO REPLACE THIS CLASS WITH EVENTVIEWPROXY BECAUSE IN THE MAIN, WE DON'T CALL IT. WE CALL IT WITH CALENDARVIEWHOLDER!!
        //Intent i = new Intent(getApplicationContext(), EventPopUpActivity.class);
        Intent i = new Intent(getApplicationContext(), EventViewProxy.class);
        startActivity(i);
    }






}