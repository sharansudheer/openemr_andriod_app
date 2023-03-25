package com.example.main_application;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;

    // code to be executed when button is clicked
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        sharedPreferences = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE);


        EditText nameField = findViewById(R.id.get_name);
        EditText passField = findViewById(R.id.get_password);


        button = (Button) findViewById(R.id.submit_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                String username2 = nameField.getText().toString();
                String password2 = passField.getText().toString();
                openNewActivity(username2,password2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // User has logged out or the session has expired, start the login process again
                // Reset the IS_LOGGED_IN value in the shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IS_LOGGED_IN", false);
                editor.apply();
            }
        }
    }



    public void openNewActivity(String username, String password) {

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
                .baseUrl("https://mobileapp.trackdemon.in/")
                .addConverterFactory(GsonConverterFactory.create())
                ;
        Retrofit retrofit = builder.build();
        ApiService apiService = retrofit.create(ApiService.class);

        Map<String, Object> map= new HashMap<>();
        map.put("grant_type", "password");
        map.put("client_id", "FTHOrCUow4SvwKhkPe7jRlLUzygTcSyzYOyUV9DTZEQ");
        map.put("scope", "openid api:oemr api:fhir user/allergy.read user/allergy.write");
        map.put("user_role", "users");
        map.put("username", username);
        map.put("password", password );



        Call<AuthResponse> call = apiService.authenticateUser(map);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    onSuccessfulAuthentication();
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
    private void onSuccessfulAuthentication() {
        // Set IS_LOGGED_IN to true
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("IS_LOGGED_IN", true);
        editor.apply();

        // Proceed to the MainPatientDashboard activity
        Intent intent = new Intent(this, MainPatientDashboard.class);
        startActivityForResult(intent, 1);
    }
}

//    public void submit() {
//        final String userName = ((TextView) findViewById(R.id.accountName)).getText().toString();
//        final String userPass = ((TextView) findViewById(R.id.accountPassword)).getText().toString();
//        new AsyncTask<Void, Void, Intent>() {
//            @Override
//            protected Intent doInBackground(Void... params) {
//                String authtoken = sServerAuthenticate.userSignIn(userName, userPass, mAuthTokenType);
//                final Intent res = new Intent();
//                res.putExtra(AccountManager.KEY_ACCOUNT_NAME, userName);
//                res.putExtra(AccountManager.KEY_ACCOUNT_TYPE, ACCOUNT_TYPE);
//                res.putExtra(AccountManager.KEY_AUTHTOKEN, authtoken);
//                res.putExtra(PARAM_USER_PASS, userPass);
//                return res;
//            }
//            @Override
//            protected void onPostExecute(Intent intent) {
//                finishLogin(intent);
//            }
//        }.execute();
//    }


//private void finishLogin(Intent intent) {
//    String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
//    String accountPassword = intent.getStringExtra(PARAM_USER_PASS);
//    final Account account = new Account(accountName, intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));
//    if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false)) {
//        String authtoken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
//        String authtokenType = mAuthTokenType;
//        // Creating the account on the device and setting the auth token we got
//        // (Not setting the auth token will cause another call to the server to authenticate the user)
//        mAccountManager.addAccountExplicitly(account, accountPassword, null);
//        mAccountManager.setAuthToken(account, authtokenType, authtoken);
//    } else {
//        mAccountManager.setPassword(account, accountPassword);
//    }
//    setAccountAuthenticatorResult(intent.getExtras());
//    setResult(RESULT_OK, intent);
//    finish();
//}


