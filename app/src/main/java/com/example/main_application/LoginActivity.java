package com.example.main_application;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import com.apicontroller.ApiService;
import com.apicontroller.AuthResponse;

import java.io.IOException;
import java.util.HashMap;

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
                    openNewActivity();
            }
        });
    }





    public void openNewActivity() {
        String username = "";
        String password = "";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                                Credentials.basic(username, password));

                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                }).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                ;

        Retrofit retrofit = builder.build();
        ApiService apiService = retrofit.create(ApiService.class);

        Map<String, Object> map= new HashMap<>();
        map.put("grant_type", "password");
        map.put("client_id", "");
        map.put("scope", "");
        map.put("user_role", "users");
        map.put("username", username);
        map.put("password", password );


        Call<AuthResponse> call = apiService.authenticateUser(map);
        Intent intent = new Intent(this, MainPatientDashboard.class);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    String accessToken = authResponse.getAccessToken();
                    Toast.makeText(getApplicationContext(), "Access token: " + accessToken, Toast.LENGTH_LONG).show();
                    //startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Broke at the is Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "No Net", Toast.LENGTH_SHORT).show();

            }
        });

     }
}





