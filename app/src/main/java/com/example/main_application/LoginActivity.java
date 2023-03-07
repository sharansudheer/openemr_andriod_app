package com.example.main_application;
import com.apicontroller.Authorize;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class LoginActivity extends AppCompatActivity {
    public void LoginHandler(View v) {
        // code to be executed when button is clicked
        EditText nameField = findViewById(R.id.get_name);
        String username = nameField.getText().toString();

        EditText passField = findViewById(R.id.get_password);
        String password = passField.getText().toString();
        Authorize tokengen = new Authorize();
        try {
            tokengen.GetAccessToken(username, password);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                Toast.makeText(this, "Check Your User Name or Password", Toast.LENGTH_SHORT).show();
            } else if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                Toast.makeText(this, "Internal Error 400", Toast.LENGTH_SHORT).show();
            } else if (ex.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                Toast.makeText(this, "Internal Error 500", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


    }
}


/*
Button button = (Button) findViewById(R.id.submit_login);

button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)*/