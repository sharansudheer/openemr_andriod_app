package hos_app.main_application;




import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import hos_app.main_application.R;
import hos_app.secrets.secrets;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Objects;
import java.util.Properties;
import java.sql.*;



public class MainPatientDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    MaterialToolbar toolBar;
    Menu menu;
    NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient_dashboard);




        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_drawer_view);
        toolBar=findViewById(R.id.topAppBar);
        navigationView.bringToFront();
        toggle=new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        menu.findItem(R.id.nav_home).setVisible(false);
        navigationView.setCheckedItem(R.id.navigation_drawer_view);
        toolBar=findViewById(R.id.topAppBar);
        setSupportActionBar(toolBar);

        Pair<Connection, String> connectionResult = getRemoteConnection(this);
        Connection connection = connectionResult.first;
        String errorMessage = connectionResult.second;

        if (connection != null) {
            Toast.makeText(MainPatientDashboard.this, "Connection woho", Toast.LENGTH_SHORT).show();

            // You can now use the connection object to interact with the database.
            // Remember to close the connection when you're done.
            try {
                connection.close();
            } catch (SQLException e) {
                Toast.makeText(MainPatientDashboard.this, "Nope", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainPatientDashboard.this, errorMessage, Toast.LENGTH_LONG).show();
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        MaterialButton buttonAppointments = findViewById(R.id.button_appointments);
        MaterialButton buttonSummary = findViewById(R.id.button_summary);
        MaterialButton buttonLedger = findViewById(R.id.button_ledger);
        MaterialButton buttonPrescriptions = findViewById(R.id.button_prescriptions);
        MaterialButton buttonAllergies = findViewById(R.id.button_allergies);

        // Set up the onClickListeners
        buttonAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Appointments.class);
                startActivity(intent);
            }
        });

        buttonSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Summary.class);
                startActivity(intent);
            }
        });

        buttonLedger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Ledger.class);
                startActivity(intent);
            }
        });

        buttonPrescriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Prescriptions.class);
                startActivity(intent);
            }
        });
        buttonAllergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientDashboard.this, Allergies.class);
                startActivity(intent);
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

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.action_logout) {
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
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }

    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int itemId = menuItem.getItemId();

             if (itemId == R.id.nav_appointments) {
                Intent intent = new Intent(MainPatientDashboard.this, Appointments.class);
                startActivity(intent);
            }
            else if (itemId ==R.id.nav_appoint_summary) {
                Intent intent1 = new Intent(MainPatientDashboard.this, Summary.class);
                startActivity(intent1);
            }

            else if (itemId == R.id.nav_ledger) {
                 Intent intent2 = new Intent(MainPatientDashboard.this, Ledger.class);
                 startActivity(intent2);
             }

            else if (itemId == R.id.nav_prescriptions){
                Intent intent4 = new Intent(MainPatientDashboard.this, Prescriptions.class);
                startActivity(intent4);
    }

            else if (itemId == R.id.nav_login) {
            menu.findItem(R.id.nav_logout).setVisible(true);
            menu.findItem(R.id.nav_profile).setVisible(true);
            menu.findItem(R.id.nav_login).setVisible(false);
        }

            else if (itemId ==R.id.nav_logout){
                menu.findItem(R.id.nav_logout).setVisible(false);
                menu.findItem(R.id.nav_profile).setVisible(false);
                menu.findItem(R.id.nav_login).setVisible(true);
                logOut();

        }
        drawerLayout.closeDrawer(GravityCompat.START);
            return true;
    }
    private KeyStore loadRDSKeyStore(Context context) throws Exception {
        InputStream inputStream = context.getAssets().open("secrets.rds_ca_2019_root.pem");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate ca = cf.generateCertificate(inputStream);
        inputStream.close();

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        return keyStore;
    }
    private Pair<Connection, String> getRemoteConnection(Context context) {
        String hostname = secrets.RDS_HOSTNAME;
        if (hostname != null) {
            try {
                // Load SSL certificate
                KeyStore keyStore = loadRDSKeyStore(context);

                // Initialize SSL context
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                tmf.init(keyStore);
                TrustManager[] trustManagers = tmf.getTrustManagers();
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustManagers, null);

                // Set connection properties
                Properties props = new Properties();
                props.setProperty("user", secrets.RDS_USERNAME);
                props.setProperty("password", secrets.RDS_PASSWORD);
                props.setProperty("ssl", "true");
                props.setProperty("sslfactory", "org.postgresql.ssl.DefaultJavaSSLFactory");
                InputStream inputStream = context.getResources().openRawResource(R.raw.rds_ca_2019_root);
                if (inputStream != null) {
                    File tempFile = File.createTempFile("rds_ca_2019_root", ".pem", context.getCacheDir());
                    try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }

                    props.setProperty("sslrootcert", tempFile.getAbsolutePath());
                    props.setProperty("sslfactory", "org.postgresql.ssl.DefaultJavaSSLFactory");
                }

                // Connect to the database
                String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + secrets.RDS_PORT + "/" + secrets.RDS_DB_NAME;
                Connection con = DriverManager.getConnection(jdbcUrl, props);
                Log.d("MyTag", "Successful");
                return new Pair<>(con, null);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String stackTrace = sw.toString();
                return new Pair<>(null, "Exception: " + e.getMessage() + "\nStack Trace:\n" + stackTrace);
            }
        }
        return new Pair<>(null, "RDS_HOSTNAME not set");
    }
}

//nav_login
//nav_profile
//nav_logout
//nav_home
//nav_appointments
//nav_appoint_summary
//nav_ledger
//nav_surgery
//nav_prescriptions