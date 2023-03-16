package com.example.main_application;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Callback;
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
        OkHttpClient client = new OkHttpClient();
        String url = "https://mobileapp.trackdemon.in/oauth2/default/token";
        String grantType = "password";
        String clientId = "FTHOrCUow4SvwKhkPe7jRlLUzygTcSyzYOyUV9DTZEQ";
        String userRole = "users";
        String scope = "openid offline_access api:oemr user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user";


        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", grantType)
                .add("client_id", clientId)
                .add("scope", scope)
                .add("user_role", userRole)
                .add("username", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(requestBody)
                .build();
        Intent intent = new Intent(this, MainPatientDashboard.class);
//
            startActivity(intent);
//
//

//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("https://mobileapp.trackdemon.in/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client);
                 // Set the custom OkHttpClient instance
//        Retrofit retrofit = builder.build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<AuthResponse> call = apiService.authenticateUser(
//                "password",
//                "FTHOrCUow4SvwKhkPe7jRlLUzygTcSyzYOyUV9DTZEQ",
//                scope,
//                "users",
//                username,
//                password
//        );
//        Intent intent = new Intent(this, MainPatientDashboard.class);
//
//
//        call.enqueue(new Callback<AuthResponse>() {
//            @Override
//            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
//                if(response.isSuccessful()){
//                    startActivity(intent);
//                }
//                else{
//                    //code broke here
//                    Toast.makeText(LoginActivity.this, "Broke at the is Success", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<AuthResponse> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "No Network", Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        try {
//            Response response = client.newCall(request).execute();
            // Do something with the response.
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

