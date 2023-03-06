package com.example.main_application;
import com.apicontroller.Authorize;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        Button button = findViewById(R.id.submit_login);
        button.setOnClickListener(v -> {
            // code to be executed when button is clicked
            EditText nameField = findViewById(R.id.get_name);
            String username = nameField.getText().toString();

            EditText passField = findViewById(R.id.get_password);
            String password = passField.getText().toString();


        });
    }
}

/*
Button button = (Button) findViewById(R.id.submit_login);

button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)*/