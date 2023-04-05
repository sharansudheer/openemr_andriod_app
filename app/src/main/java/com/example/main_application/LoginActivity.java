package com.example.main_application;

import com.apicontroller.ApiService;
import com.apicontroller.AuthResponse;
import com.secrets.Secrets;
import com.data.EntityToken;
import com.data.AppDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;



import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class LoginActivity extends AppCompatActivity {


    // code to be executed when button is clicked
    Button button;
    private ExecutorService executorService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        executorService = Executors.newSingleThreadExecutor();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        //SharedPreferences sharedPreferences = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE);


        EditText nameField = findViewById(R.id.get_name);
        EditText passField = findViewById(R.id.get_password);


        button = (Button) findViewById(R.id.submit_login);
        button.setOnClickListener(v -> {
            String username2 = nameField.getText().toString();
            String password2 = passField.getText().toString();
            openNewActivity(username2, password2);
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
        map.put("client_id", Secrets.CLIENT_ID);
        map.put("scope", Secrets.SCOPE);
        map.put("user_role", Secrets.USER_ROLE);
        map.put("username", username);
        map.put("password", password);


        Call<AuthResponse> call = apiService.authenticateUser(map);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    if (authResponse != null && authResponse.getRefreshToken() != null) {

                        String refreshToken = authResponse.getRefreshToken();
                        saveRefreshToken(LoginActivity.this, refreshToken);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();

                    } else {
                        Toast.makeText(LoginActivity.this, "Authentication response is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "No Net", Toast.LENGTH_SHORT).show();

            }
        });
    }



    private void saveRefreshToken(LoginActivity activity, String token) {
        Callable<Void> saveRefreshTokenTask = () -> {
            AppDatabase appDatabase = AppDatabase.getDatabase(activity);
            EntityToken tokens = new EntityToken(1, token);
            appDatabase.refreshTokenDao().saveRefreshToken(tokens);
            return null;
        };

       executorService.submit(saveRefreshTokenTask);
        // Optionally, you can handle the result of the task here using future.get()
    }
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
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



//    In Java, the ... (three dots) notation is called "varargs"
//    (short for variable number of arguments). It is a feature that allows us to pass a variable
//    number of arguments of the same type to a method.
// When we see a String... tokens in a method's parameter list,
// it means the method can accept zero or more String arguments.
// Under the hood, these arguments will be treated as an array.
//doInBackground accepts a varargs parameter of type String,
// meaning it can be called with any number of String arguments.
// However, when calling execute() on an AsyncTask, we can generally pass the arguments that will be received by
// doInBackground().
