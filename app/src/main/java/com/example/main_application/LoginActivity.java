package com.example.main_application;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;


import android.widget.Toast;


import com.apicontroller.ApiService;
import com.apicontroller.AuthResponse;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private Context context;


    public void LoginHandler(View v) {
        context = getApplicationContext();

        // code to be executed when button is clicked
        EditText nameField = findViewById(R.id.get_name);
        String username = nameField.getText().toString();
        EditText passField = findViewById(R.id.get_password);
        String password = passField.getText().toString();

        // Use the context variable to get an instance of the AccountManager class
        AccountManager accountManager = AccountManager.get(context);
        Account account = new Account(username, "com.example.main_application.ACCOUNT_TYPE");
        accountManager.addAccountExplicitly(account, password, null);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiService apiService = retrofit.create(com.apicontroller.ApiService.class);
        Call<AuthResponse> call = apiService.authenticateUser(
                "password",
                "LnjqojEEjFYe5j2Jp9m9UnmuxOnMg4VodEJj3yE8_OA",
                "openid offline_access api:oemr api:fhir user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user/dental_issue.write user/document.read user/document.write user/drug.read user/encounter.read user/encounter.write user/facility.read user/facility.write user/immunization.read user/insurance.read user/insurance.write user/insurance_company.read user/insurance_company.write user/insurance_type.read user/list.read user/medical_problem.read user/medical_problem.write user/medication.read user/medication.write user/message.write user/patient.read user/patient.write user/practitioner.read user/practitioner.write user/prescription.read user/procedure.read user/soap_note.read user/soap_note.write user/surgery.read user/surgery.write user/transaction.read user/transaction.write user/vital.read user/vital.write user/AllergyIntolerance.read user/CareTeam.read user/Condition.read user/Coverage.read user/Encounter.read user/Immunization.read user/Location.read user/Medication.read user/MedicationRequest.read user/Observation.read user/Organization.read user/Organization.write user/Patient.read user/Patient.write user/Practitioner.read user/Practitioner.write user/PractitionerRole.read user/Procedure.read",
                "users",
                username,
                password
        );
        // Initialize the context variable
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(v.getContext(), MainPatientDashboard.class);
                    startActivity(intent);

                } else {
                    // Handle error
                }
            }
            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // Handle error
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


    }
}


