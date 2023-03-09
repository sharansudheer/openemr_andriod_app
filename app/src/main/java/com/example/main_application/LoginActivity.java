package com.example.main_application;
//import com.apicontroller.Authorize;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {
    public void LoginHandler(View v) {
        // code to be executed when button is clicked
        EditText nameField = findViewById(R.id.get_name);
        String username = nameField.getText().toString();

        EditText passField = findViewById(R.id.get_password);
        String password = passField.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


    }
}


