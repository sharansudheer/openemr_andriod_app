package com.example.main_application;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPatientDashboard extends AppCompatActivity {
    Button call_appointment;
    Button call_billing;
    Button call_prescription;
    Button call_allergies;
    Button call_summary;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient_dashboard);

         //Get shared preferences object
        sharedPreferences = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE);
        // Check if user is logged in
        boolean isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false);
        Intent intent;
        if (!isLoggedIn) {
            // User is not logged in, start login activity
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);


        } else {


            call_appointment = findViewById(R.id.goto_appointments);
            call_appointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNewActivity();
                }
            });

            call_billing = findViewById(R.id.goto_billing);
            call_billing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNewBilling();
                }
            });

        }
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
    private void onUserLoggedOut() {
        // Set the result and finish the activity
        setResult(RESULT_OK);
        finish();
    }

}
