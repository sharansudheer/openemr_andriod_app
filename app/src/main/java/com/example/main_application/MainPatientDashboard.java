package com.example.main_application;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

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

        TableLayout tblLayout = (TableLayout) findViewById(R.id.button_table);
        TableRow row = (TableRow)tblLayout.getChildAt(0); // Here get row id depending on number of row
        Button button = (Button)row.getChildAt(1); // get
        call_appointment = (Button) findViewById(R.id.goto_appointments);
        call_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });


        TableRow row1 = (TableRow)tblLayout.getChildAt(0); // Here get row id depending on number of row
        Button button2 = (Button)row1.getChildAt(0);
        call_billing = (Button) findViewById(R.id.goto_billing);
        call_billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewBilling();
            }
        });
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