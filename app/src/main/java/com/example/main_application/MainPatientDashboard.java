package com.example.main_application;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;

public class MainPatientDashboard extends AppCompatActivity {
    Button call_appointment;
    Button call_billing;
    Button call_prescription;
    Button call_allergies;
    Button call_summary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient_dashboard);

        call_appointment = findViewById(R.id.goto_appointments);
        call_appointment.setOnClickListener(v -> openNewActivity());

        call_billing = findViewById(R.id.goto_billing);
        call_billing.setOnClickListener(v -> openNewBilling());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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


    public void openNewActivity(){
        Intent intent = new Intent(this, Appointments.class);
        startActivity(intent);
    }
    public void openNewBilling(){
        Intent intent = new Intent(this, Ledger.class);
        startActivity(intent);

    }


}
