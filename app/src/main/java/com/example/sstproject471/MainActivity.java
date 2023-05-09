package com.example.sstproject471;

import android.os.Bundle;

import com.example.sstproject471.fragments.AddEventFragment;
import com.example.sstproject471.fragments.CalendarViewFragment;
import com.example.sstproject471.fragments.HomeFragment;
import com.example.sstproject471.fragments.RecommendMeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sstproject471.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        CalendarViewFragment calendarViewFragment = new CalendarViewFragment();
        RecommendMeFragment recommendMeFragment = new RecommendMeFragment();
        AddEventFragment addEventFragment = new AddEventFragment();
        HomeFragment homeFragment = new HomeFragment();

        changeFragment(calendarViewFragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.ic_calendar) {
                    changeFragment(calendarViewFragment);
                    bottomNavigationView.getMenu().findItem(R.id.ic_calendar).setIcon(R.drawable.ic_calendar_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_recommend_me).setIcon(R.drawable.ic_recommend_me_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_home).setIcon(R.drawable.ic_home_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_add_event).setIcon(R.drawable.ic_add_event_drawable);
                    return true;
                } else if (itemId == R.id.ic_recommend_me) {
                    changeFragment(recommendMeFragment);
                    bottomNavigationView.getMenu().findItem(R.id.ic_calendar).setIcon(R.drawable.ic_calendar_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_recommend_me).setIcon(R.drawable.ic_recommend_me_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_home).setIcon(R.drawable.ic_home_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_add_event).setIcon(R.drawable.ic_add_event_drawable);
                    return true;
                } else if (itemId == R.id.ic_home) {
                    changeFragment(homeFragment);
                    bottomNavigationView.getMenu().findItem(R.id.ic_calendar).setIcon(R.drawable.ic_calendar_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_recommend_me).setIcon(R.drawable.ic_recommend_me_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_home).setIcon(R.drawable.ic_home_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_add_event).setIcon(R.drawable.ic_add_event_drawable);
                    return true;
                } else if (itemId == R.id.ic_add_event) {
                    changeFragment(addEventFragment);
                    bottomNavigationView.getMenu().findItem(R.id.ic_calendar).setIcon(R.drawable.ic_calendar_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_recommend_me).setIcon(R.drawable.ic_recommend_me_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_home).setIcon(R.drawable.ic_home_drawable);
                    bottomNavigationView.getMenu().findItem(R.id.ic_add_event).setIcon(R.drawable.ic_add_event_drawable);
                    return true;
                }
                return false;
            }
        });

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fContainer, fragment)
                .commit();
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