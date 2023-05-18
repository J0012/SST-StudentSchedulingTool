package com.example.sstdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sstdatabase.R;
import com.example.sstdatabase.databinding.ActivityHomePageBinding;

public class HomePageActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHomePageBinding binding;

    Button addEventButton;
    Button viewCalendarButton;
    Button RecommendMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        //This is for our button listener for our View Calendar Button(The next 7 lines)
        addEventButton = (Button) findViewById(R.id.AddEventButton); //we must make a button finder and it will find the calendar button id we make for "View Calendar" button in our main activity
        addEventButton.setOnClickListener(new View.OnClickListener() { //we made a button listener function
            @Override
            public void onClick(View view) {
                //openActivity2(); //we call the function openActivity2 to do the action of opening activity2(which is a new page for viewing the calendar)
                Intent i = new Intent(HomePageActivity.this, AddEventActivity.class);
                startActivity(i);
            }
        });

        viewCalendarButton = (Button) findViewById(R.id.ViewCalendarButton); //we must make a button finder and it will find the calendar button id we make for "View Calendar" button in our main activity
        viewCalendarButton.setOnClickListener(new View.OnClickListener() { //we made a button listener function
            @Override
            public void onClick(View view) {
                //openActivity2(); //we call the function openActivity2 to do the action of opening activity2(which is a new page for viewing the calendar)
                Intent i = new Intent(HomePageActivity.this, CalendarLauncher.class);
                startActivity(i);
            }
        });
    }


    //************ THIS SECTION ADDS THE TOOLBAR AT THE TOP!!!!
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
        if (id == R.id.action_settings) {// this is the name of the 3 dots at the top!!

            Intent i = new Intent(HomePageActivity.this, SettingsActivity.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //*********** END OF 3 dots section!!!

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}