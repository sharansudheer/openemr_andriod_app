package hos_app.main_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;


import java.util.Objects;



public class LoginActivity extends AppCompatActivity {

    // code to be executed when button is clicked
    Button button;


    private TextInputEditText passField;
    private TextInputEditText nameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        nameField = findViewById(R.id.get_name);
        passField = findViewById(R.id.get_password);
        button = findViewById(R.id.submit_login);
        button.setOnClickListener(v -> {

            String username2 = Objects.requireNonNull(nameField.getText()).toString();
            String password2 = Objects.requireNonNull(passField.getText()).toString();
            if(!username2.isEmpty() && !password2.isEmpty()){
                    openNewActivity(username2,password2);
                }
            else{

                Toast.makeText(LoginActivity.this, "Please Enter the Username or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void openNewActivity(String username, String password) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference currentUserRef = usersRef.child(username);

        currentUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storedPassword = dataSnapshot.child("password").getValue(String.class);
                    String role = dataSnapshot.child("role").getValue(String.class);
                    String practitionerName = dataSnapshot.getKey();



                    if (storedPassword != null && storedPassword.equals(password)) {

                        Intent intent;
                        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", true);
                        editor.putString("role", role);
                        editor.putString("practitionerName", practitionerName);
                        editor.apply();

                        if ("patient".equals(role)) {
                            intent = new Intent(LoginActivity.this, MainPatientDashboard.class);
                        } else {
                            intent = new Intent(LoginActivity.this, DoctorDashboard.class);
                        }
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
