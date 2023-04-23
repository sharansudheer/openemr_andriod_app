package hos_app.main_application;

//import com.apicontroller.ApiService;
//import com.apicontroller.AuthResponse;
//
//import com.secrets.Secrets;
//import com.data.EntityToken;
//import com.data.AppDatabase;
//
//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;


import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;




//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.Call;
//import retrofit2.Retrofit;
//
//
//
//import java.util.Map;
//import java.util.HashMap;
import java.util.Objects;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;



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
        String expectedUsername = "Phillip Andrews";
        String expectedPassword = "Andrews";


        if (username.equals(expectedUsername) && password.equals(expectedPassword)) {
            SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", true);
            editor.apply();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
        }
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


//TextInputLayout passInputLayout = findViewById(R.id.password_layout);
//ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.custom_stroke_color));
//passInputLayout.setBoxStrokeColorStateList(colorStateList);