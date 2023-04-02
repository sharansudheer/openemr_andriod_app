package com.example.main_application;


import com.data.EntityToken;
import com.data.AppDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;



import com.apicontroller.ApiService;
import com.apicontroller.AuthResponse;


import java.util.HashMap;



public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    // code to be executed when button is clicked
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        sharedPreferences = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE);


        EditText nameField = findViewById(R.id.get_name);
        EditText passField = findViewById(R.id.get_password);


        button = (Button) findViewById(R.id.submit_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username2 = nameField.getText().toString();
                String password2 = passField.getText().toString();
                openNewActivity(username2, password2);
            }
        });
    }


    public void openNewActivity(String username, String password) {


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://mobileapp.trackdemon.in/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ApiService apiService = retrofit.create(ApiService.class);

        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "password");
        map.put("client_id", "FTHOrCUow4SvwKhkPe7jRlLUzygTcSyzYOyUV9DTZEQ");
        map.put("scope", "openid api:oemr api:fhir user/allergy.read user/allergy.write");
        map.put("user_role", "users");
        map.put("username", username);
        map.put("password", password);


        Call<AuthResponse> call = apiService.authenticateUser(map);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    String refreshToken = authResponse.getAccessToken();
                    EditText nameField = findViewById(R.id.get_name);
                    if (refreshToken != null) {
                        nameField.setText(refreshToken);
                        } else {
                        nameField.setText("NULL");
                        }

                } else {
                    Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "No Net", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void onSuccessfulAuthentication(String refreshToken) {
        // Set IS_LOGGED_IN to true
        new SaveRefreshTokenTask().execute(refreshToken);


    }

    private class SaveRefreshTokenTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... tokens) {
            String refreshToken = tokens[0];
            AppDatabase appDatabase = AppDatabase.getDatabase(LoginActivity.this);
            EntityToken token = new EntityToken(1, refreshToken);
            appDatabase.refreshTokenDao().saveRefreshToken(token);
            return null;
        }

    }
}

//    AuthResponse authResponse = response.body();
//                    String refreshToken = authResponse.getAccessToken();
//                    EditText nameField = findViewById(R.id.get_name);
//                    if (refreshToken != null) {
//                        nameField.setText(refreshToken);
//                        } else {
//                        nameField.setText("NULL");
//                        }

//    String refreshToken = authResponse.getRefreshToken();
//                    Toast.makeText(getApplicationContext(), refreshToken, Toast.LENGTH_SHORT).show();
//
//                            if (authResponse != null) {
//
//                            onSuccessfulAuthentication(authResponse.getRefreshToken());
//                            SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putBoolean("isLoggedIn", true);
//                            editor.apply();
//
//                            } else {
//                            Toast.makeText(LoginActivity.this, "Authentication response is empty", Toast.LENGTH_SHORT).show();
//                            }