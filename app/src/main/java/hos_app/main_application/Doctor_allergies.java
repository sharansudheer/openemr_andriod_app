package hos_app.main_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
public class Doctor_allergies extends AppCompatActivity {

    MaterialToolbar toolBar;
    TextView selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_allergies);

        toolBar=findViewById(R.id.topAppBar);
        setSupportActionBar(toolBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        selectedDate = findViewById(R.id.get_date);

//        findViewById(R.id.).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showMaterialDatePicker();
//            }
//        });
//

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);

        EditText getAllergyEditText = findViewById(R.id.get_allergy);
        EditText getDateEditText = findViewById(R.id.get_date);
        Button submitAllergiesButton = findViewById(R.id.submit_allergies);
        String practitionerName = sharedPreferences.getString("practitionerName", "");
        TextInputEditText getPractitionerEditText = findViewById(R.id.get_practitioner);

        getPractitionerEditText.setText(practitionerName);
        getPractitionerEditText.setEnabled(false);
        submitAllergiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String allergy = getAllergyEditText.getText().toString().trim();
                String date = getDateEditText.getText().toString().trim();
                String practitionerName = sharedPreferences.getString("practitionerName", "");
                // Proceed with saving the data to Firebase
                DatabaseReference userAllergiesRef = FirebaseDatabase.getInstance().getReference("users/Phillip Andrews/allergies");
                Map<String, Object> allergyData = new HashMap<>();
                allergyData.put("Allergy", allergy);
                allergyData.put("Practitioner", practitionerName);

                userAllergiesRef.child(date).setValue(allergyData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Handle success, e.g., show a success message or update the UI
                                getAllergyEditText .setText("");
                                getDateEditText.setText("");
                                Toast.makeText(getApplicationContext(), "Allergy data saved successfully!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle failure, e.g., show an error message
                                Toast.makeText(getApplicationContext(), "Error saving allergy data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
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

    private void showMaterialDatePicker() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a date");

        // Optional: Setting CalendarConstraints to disable past dates
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());
        builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<Long> picker = builder.build();

        picker.addOnPositiveButtonClickListener(selection -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            String formattedDate = sdf.format(selection);
            selectedDate.setText(formattedDate);
        });

        picker.show(getSupportFragmentManager(), picker.toString());
    }

    @Override
    public void onBackPressed() {
        // Your custom behavior, e.g., show a confirmation dialog or navigate to a specific screen
    }



}