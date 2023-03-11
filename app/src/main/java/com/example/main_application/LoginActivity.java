package com.example.main_application;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
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

        // Initialize the context variable
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
                .baseUrl("https://api.example.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiService apiService = retrofit.create(com.apicontroller.ApiService.class);
        Call<AuthResponse> call = apiService.authenticateUser(
                "password",
                "",
                '',
                username,
                password
        );
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse tokenResponse = response.body();
                    // Do something with the tokenResponse
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


