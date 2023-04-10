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

import com.apicontroller.AppointmentResponse;
import com.apicontroller.GetAppointments;
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
    public void fetchAppointments(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mobileapp.trackdemon.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAppointments getAppointment = retrofit.create(GetAppointments.class);
        Call<List<AppointmentResponse>> call = getAppointment.getAppointments(
                "1",
                "application/json",
                "Bearer " + token
        );

        call.enqueue(new Callback<List<AppointmentResponse>>() {
            @Override
            public void onResponse(Call<List<AppointmentResponse>> call, Response<List<AppointmentResponse>> response) {
                if (response.isSuccessful()) {
                    List<AppointmentResponse> appointments = response.body();

                    // Initialize the adapter with the list of AppointmentResponse objects
                    AppointmentAdapter appointmentAdapter = new AppointmentAdapter(appointments);

                    // Set the adapter to the RecyclerView
                    RecyclerView recyclerView = findViewById(R.id.my_recyclerview);
                    GridLayoutManager layoutManager = new GridLayoutManager(Appointments.this, 2); // Change the second parameter to the number of columns you want
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(appointmentAdapter);
                } else {
                    // Handle the error case
                }
            }

            @Override
            public void onFailure(Call<List<AppointmentResponse>> call, Throwable t) {
                // Handle the failure case
            }

        });
    }
}