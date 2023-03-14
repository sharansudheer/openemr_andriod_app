package com.example.main_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;


import com.apicontroller.ApiService;
import com.apicontroller.AuthResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    // code to be executed when button is clicked
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        EditText nameField = findViewById(R.id.get_name);
        String username2 = nameField.getText().toString();
        EditText passField = findViewById(R.id.get_password);
        String password2 = passField.getText().toString();
        button = (Button) findViewById(R.id.submit_login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(username2,password2);
            }
        });
    }





    public void openNewActivity(String username,String password) {


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://mobileapp.trackdemon.in/")
                .addConverterFactory(GsonConverterFactory.create());
                 // Set the custom OkHttpClient instance
        String scope = "openid offline_access api:oemr user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user";
        Toast.makeText(this, "Broke at encoding", Toast.LENGTH_SHORT).show();

        try {
            // code that may throw UnsupportedEncodingException
            String encodedScope = URLEncoder.encode(scope, "UTF-8");
            Retrofit retrofit = builder.build();
            ApiService apiService = retrofit.create(ApiService.class);
            Call<AuthResponse> call = apiService.authenticateUser(
                    "password",
                    "FTHOrCUow4SvwKhkPe7jRlLUzygTcSyzYOyUV9DTZEQ",
                    encodedScope,
                    "users",
                    username,
                    password
            );
            Intent intent = new Intent(this, MainPatientDashboard.class);
            call.enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    if(response.isSuccessful()){
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                }
            });
        } catch (UnsupportedEncodingException e) {
            // handle the exception
            Toast.makeText(this, "Broke at encoding", Toast.LENGTH_SHORT).show();
        }




    }
}

