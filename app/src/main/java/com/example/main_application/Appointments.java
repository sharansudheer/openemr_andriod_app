package com.example.main_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.apicontroller.AppointmentResponse;
import com.apicontroller.GetAppointments;
import com.viewadapters.*;
import com.data.EntityToken;
import com.google.android.material.appbar.MaterialToolbar;
import com.viewadapters.AppointmentAdapter;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Appointments extends AppCompatActivity {
    MaterialToolbar toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        toolBar=findViewById(R.id.topAppBar);
        setSupportActionBar(toolBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)  {
            Intent intent = new Intent(Appointments.this,MainPatientDashboard.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.action_logout) {
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
//get the token from refresh token
    public void fetchAppointments() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mobileapp.trackdemon.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAppointments getAppointment = retrofit.create(GetAppointments.class);
        Call<List<AppointmentResponse>> call = getAppointment.getAppointments(
                "1",
                "application/json",
                "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJGVEhPckNVb3c0U3Z3S2hrUGU3alJsTFV6eWdUY1N5ellPeVVWOURUWkVRIiwianRpIjoiYzFlMGYxODA5ZTlhOTZkYjRiMTAwZDQ1ZWM1N2Q5ZDRlYTk5Y2I1ZThiNzIwMThlNzFmMjg1OGM2OTY4MWZiYTI3OTE3Y2E0MmQzZDg2YTQiLCJpYXQiOjE2ODExOTY3MjAuMTY3NTUyLCJuYmYiOjE2ODExOTY3MjAuMTY3NTU2LCJleHAiOjE2ODEyMDAzMjAuMTUzNDg5LCJzdWIiOiI5ODhiZTI4Zi0xYTdkLTRiMDQtOGM4Mi03ODViY2YzZDhlMDQiLCJzY29wZXMiOlsib3BlbmlkIiwib2ZmbGluZV9hY2Nlc3MiLCJhcGk6b2VtciIsImFwaTpmaGlyIiwidXNlci9hcHBvaW50bWVudC5yZWFkIiwidXNlci9hcHBvaW50bWVudC53cml0ZSIsInNpdGU6ZGVmYXVsdCJdfQ.tSN9CxxLf_v6QCxzK0JzlY9JH_7yJEIzAT7ZMyDeZdmJy-hSJ_twSJr6q9IWIWOv8foaMNUEmGkgQ__E396qTDk9UCTMFP7-3RrvkV3EPSOvphZcM9g96T1QFy5hKjEG0R5jdN1aphjRpMoWfqc0ezZjtNQ3_ZEkM5rWBen7AQk0vcfK-SoJIhdzogMFTG-vRXfvvJFp5MV0kwDVTrCjHJLN6VQ4XOMgXlsfUEpzPefb8gjFvp_k86feAHjxgSJRtR4T6XgX0wV3ffS_Z2RUBoDj1EWu13jkpOIjC4DQtlJT5bO87SALvlc5LAN7MQuMBaA3y7oU52eed6XgOjrOWw"
        );

        call.enqueue(new Callback<List<AppointmentResponse>>() {
            @Override
            public void onResponse(Call<List<AppointmentResponse>> call, Response<List<AppointmentResponse>> response) {
                if (response.isSuccessful()) {
                    List<AppointmentResponse> appointments = response.body();

                    // Initialize the adapter with the list of AppointmentResponse objects
                    AppointmentAdapter appointmentAdapter = new AppointmentAdapter(appointments);

                    // Set the adapter to the RecyclerView

                } else {
                    // Handle the error case
                    Toast.makeText(Appointments.this, "Authentication response is empty", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<AppointmentResponse>> call, Throwable t) {
                // Handle the failure case
                Toast.makeText(Appointments.this, "No Net", Toast.LENGTH_SHORT).show();

            }

        });
    }
}