package hos_app.main_application;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.HashMap;

import java.util.Map;
import java.util.Objects;
import com.google.android.material.datepicker.MaterialDatePicker;

public class Doctor_allergies extends AppCompatActivity {

    MaterialToolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_allergies);

        toolBar=findViewById(R.id.topAppBar);
        setSupportActionBar(toolBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        MaterialDatePicker.Builder<Long> datePickerBuilder = MaterialDatePicker.Builder.datePicker();
        datePickerBuilder.setTitleText("Select date");
        MaterialDatePicker<Long> datePicker = datePickerBuilder.build();
        TextInputEditText getDateEditText = findViewById(R.id.get_date);

        getDateEditText.setOnClickListener(v -> datePicker.show(getSupportFragmentManager(), datePicker.getTag()));

        datePicker.addOnPositiveButtonClickListener(selection -> {
            String selectedDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(selection);
            getDateEditText.setText(selectedDate);
        });

        datePicker.addOnNegativeButtonClickListener(v -> datePicker.dismiss());
        datePicker.addOnCancelListener(dialog -> Toast.makeText(Doctor_allergies.this, "Date selection canceled", Toast.LENGTH_SHORT).show());

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        EditText getAllergyEditText = findViewById(R.id.get_allergy);
        Button submitAllergiesButton = findViewById(R.id.submit_allergies);
        String practitionerName = sharedPreferences.getString("practitionerName", "");
        TextInputEditText getPractitionerEditText = findViewById(R.id.get_practitioner);

        getPractitionerEditText.setText(practitionerName);
        getPractitionerEditText.setEnabled(false);
        submitAllergiesButton.setOnClickListener(v -> {
            //TODO
            //MAKE SURE THAT ALL THE FIELDS ARE NOT NULL ELSE DON'T ACCEPT THE INPUT
            // FIX NO NETWORK ISSUE, IF NO NETWORK, DON'T OPEN THE APP
            //
            //
            //
            ////
            String allergy = getAllergyEditText.getText().toString().trim();
            String date = Objects.requireNonNull(getDateEditText.getText()).toString().trim();
            String practitionerName1 = sharedPreferences.getString("practitionerName", "");
            // Proceed with saving the data to Firebase
            DatabaseReference userAllergiesRef = FirebaseDatabase.getInstance().getReference("users/Phillip Andrews/allergies");
            Map<String, Object> allergyData = new HashMap<>();
            allergyData.put("Allergy", allergy);
            allergyData.put("Practitioner", practitionerName1);

            userAllergiesRef.child(date).setValue(allergyData)
                    .addOnSuccessListener(aVoid -> {
                        getAllergyEditText .setText("");
                        getDateEditText.setText("");
                        Toast.makeText(getApplicationContext(), "Allergy data saved successfully!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Handle failure, e.g., show an error message
                        Toast.makeText(getApplicationContext(), "Error saving allergy data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)  {
            Intent intent = new Intent(Doctor_allergies.this,DoctorDashboard.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.action_logout) {
            logOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logOut() {
        // log out logic here
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        setResult(RESULT_OK);
        finish();
    }



    @Override
    public void onBackPressed() {
        // Your custom behavior, e.g., show a confirmation dialog or navigate to a specific screen
    }



}