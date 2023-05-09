package com.example.sstproject471.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sstproject471.CalendarAdapter;
import com.example.sstproject471.CalendarViewHolder;
import com.example.sstproject471.EventViewProxy;
import com.example.sstproject471.R;
import com.example.sstproject471.databinding.ActivityMainBinding;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarViewFragment extends Fragment {
    private RecyclerView recyclerView;
    // private MyAdapter adapter1;
    // private MyAdapter adapter2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private TextView monthYearText;
    private RecyclerView calenderRecyclerView;
    private LocalDate selectedDate;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarViewFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CalendarViewFragment newInstance(String param1, String param2) {
        CalendarViewFragment fragment = new CalendarViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_calendar_view, container, false);
        recyclerView = view.findViewById(R.id.calendarRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
       // mAdapter = new CalendarAdapter(daysofMonth);
        recyclerView.setAdapter(mAdapter);

        initwidgets(view);
        selectedDate = LocalDate.now();
        setMonthView();

        return view;
    }

    private void initwidgets(View view) {
        calenderRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, (CalendarAdapter.OnItemListener) this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 7);
        calenderRecyclerView.setLayoutManager(layoutManager);
        calenderRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek)
                daysInMonthArray.add("");
            else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get a reference to the activity and set up the toolbar
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
        //activity.setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(view);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

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

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


    // ESSENTIALLY WHEN THE USER CLICKS ON A DATE, THEY WILL OPEN UP THE POP UP!!!
    // USED BY THE PUBLIC INTERFACE JAROD MADE!!! WE ARE USING THE FUNCTION PASSED BY THE INTERFACE. INTERFACE IS LIKE A BRIDGE!!
//    @Override
//    public void onItemClick(int position, String dayText) {
//
//        //if(dayText.equals("")){
//        String message = "Selected Date: " + dayText + " " + monthYearFromDate(selectedDate);
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//        //}
//
//
//        // WE LAUNCH THE POP UP RIGHT HERE!!! //WILL NEED TO REPLACE THIS CLASS WITH EVENTVIEWPROXY BECAUSE IN THE MAIN, WE DON'T CALL IT. WE CALL IT WITH CALENDARVIEWHOLDER!!
//        //Intent i = new Intent(getApplicationContext(), EventPopUpActivity.class);
//        Intent i = new Intent(getApplicationContext(), EventViewProxy.class);
//        startActivity(i);
//    }


}