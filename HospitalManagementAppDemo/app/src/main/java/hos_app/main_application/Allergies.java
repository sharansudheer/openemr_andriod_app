package hos_app.main_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import hos_app.main_application.R;
import hos_app.recyclerview_Adapters.Allergy;
import hos_app.recyclerview_Adapters.AllergyAdapter;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Allergies extends AppCompatActivity {
    MaterialToolbar toolBar;

    private RecyclerView recyclerView;
    private AllergyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies);
        toolBar=findViewById(R.id.topAppBar);
        setSupportActionBar(toolBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AllergyAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        DatabaseReference allergiesRef = FirebaseDatabase.getInstance().getReference("users/Phillip Andrews/allergies");
        allergiesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Allergy> allergyList = new ArrayList<>();
                for (DataSnapshot allergySnapshot : dataSnapshot.getChildren()) {
                    String date = allergySnapshot.getKey();
                    String allergy = allergySnapshot.child("Allergy").getValue(String.class);
                    String practitioner = allergySnapshot.child("Practitioner").getValue(String.class);
                    Allergy allergyData = new Allergy(date, allergy, practitioner);
                    allergyList.add(allergyData);
                }
                adapter.updateAllergyList(allergyList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
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
            Intent intent = new Intent(Allergies.this,MainPatientDashboard.class);
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
}