package hos_app.main_application;




import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;



import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;




public class MainPatientDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    MaterialToolbar toolBar;
    Menu menu;
    NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient_dashboard);




        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_drawer_view);
        toolBar=findViewById(R.id.topAppBar);
        navigationView.bringToFront();
        toggle=new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        menu.findItem(R.id.nav_home).setVisible(false);
        navigationView.setCheckedItem(R.id.navigation_drawer_view);
        toolBar=findViewById(R.id.topAppBar);
        setSupportActionBar(toolBar);



        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        MaterialButton buttonAppointments = findViewById(R.id.button_appointments);
        MaterialButton buttonSummary = findViewById(R.id.button_summary);
        MaterialButton buttonLedger = findViewById(R.id.button_ledger);
        MaterialButton buttonPrescriptions = findViewById(R.id.button_prescriptions);
        MaterialButton buttonAllergies = findViewById(R.id.button_allergies);




        // Set up the onClickListeners
        buttonAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Appointments.class);
                startActivity(intent);
            }
        });

        buttonSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Summary.class);
                startActivity(intent);
            }
        });

        buttonLedger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Ledger.class);
                startActivity(intent);
            }
        });

        buttonPrescriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Prescriptions.class);
                startActivity(intent);
            }
        });
        buttonAllergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Allergies.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.action_logout) {
            logOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void logOut() {
        // log out logic here
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }

    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int itemId = menuItem.getItemId();

             if (itemId == R.id.nav_appointments) {
                Intent intent = new Intent(MainPatientDashboard.this, Appointments.class);
                startActivity(intent);
            }
            else if (itemId ==R.id.nav_appoint_summary) {
                Intent intent1 = new Intent(MainPatientDashboard.this, Summary.class);
                startActivity(intent1);
            }

            else if (itemId == R.id.nav_ledger) {
                 Intent intent2 = new Intent(MainPatientDashboard.this, Ledger.class);
                 startActivity(intent2);
             }

            else if (itemId == R.id.nav_prescriptions){
                Intent intent4 = new Intent(MainPatientDashboard.this, Prescriptions.class);
                startActivity(intent4);
    }

            else if (itemId == R.id.nav_login) {
            menu.findItem(R.id.nav_logout).setVisible(true);
            menu.findItem(R.id.nav_profile).setVisible(true);
            menu.findItem(R.id.nav_login).setVisible(false);
        }

            else if (itemId ==R.id.nav_logout){
                menu.findItem(R.id.nav_logout).setVisible(false);
                menu.findItem(R.id.nav_profile).setVisible(false);
                menu.findItem(R.id.nav_login).setVisible(true);
                logOut();

        }
        drawerLayout.closeDrawer(GravityCompat.START);
            return true;
    }




}

//nav_login
//nav_profile
//nav_logout
//nav_home
//nav_appointments
//nav_appoint_summary
//nav_ledger
//nav_surgery
//nav_prescriptions