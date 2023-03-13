package com.example.main_application;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


import com.apicontroller.ApiService;
import com.apicontroller.AuthResponse;


import retrofit2.Call;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    // code to be executed when button is clicked
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        EditText nameField = findViewById(R.id.get_name);
        String username = nameField.getText().toString();
        EditText passField = findViewById(R.id.get_password);
        String password = passField.getText().toString();
        button = (Button) findViewById(R.id.submit_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, MainPatientDashboard.class);
        startActivity(intent);
    }
    public void login(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://mobileapp.trackdemon.in/")
                .addConverterFactory(GsonConverterFactory.create())
                ;
        Retrofit retrofit = builder.build();
        ApiService apiService = retrofit.create(ApiService.class);
//            Call<AuthResponse> call = apiService.authenticateUser(
//                    "password",
//                    "LnjqojEEjFYe5j2Jp9m9UnmuxOnMg4VodEJj3yE8_OA",
//                    "openid offline_access api:oemr api:fhir user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user/dental_issue.write user/document.read user/document.write user/drug.read user/encounter.read user/encounter.write user/facility.read user/facility.write user/immunization.read user/insurance.read user/insurance.write user/insurance_company.read user/insurance_company.write user/insurance_type.read user/list.read user/medical_problem.read user/medical_problem.write user/medication.read user/medication.write user/message.write user/patient.read user/patient.write user/practitioner.read user/practitioner.write user/prescription.read user/procedure.read user/soap_note.read user/soap_note.write user/surgery.read user/surgery.write user/transaction.read user/transaction.write user/vital.read user/vital.write user/AllergyIntolerance.read user/CareTeam.read user/Condition.read user/Coverage.read user/Encounter.read user/Immunization.read user/Location.read user/Medication.read user/MedicationRequest.read user/Observation.read user/Organization.read user/Organization.write user/Patient.read user/Patient.write user/Practitioner.read user/Practitioner.write user/PractitionerRole.read user/Procedure.read",
//                    "users",
//                    username,
//                    password
//            );


    }
}

