public class LoginActivity extends AppCompatActivity {

    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        
        nameField = findViewById(R.id.get_name);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username2 = nameField.getText().toString();
                // Do something with the username
            }
        });
    }
}
public void logout(Account account, String accessToken, Context context) {
    // Revoke the access token
    OkHttpClient client = new OkHttpClient();
    RequestBody requestBody = new FormBody.Builder()
            .add("token", accessToken)
            .build();
    Request request = new Request.Builder()
            .url("https://your-api.com/logout")
            .post(requestBody)
            .header("Authorization", "Bearer " + accessToken)
            .build();
    try {
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            // Access token revoked
        } else {
            // Handle error
        }
    } catch (IOException e) {
        // Handle network error
    }

    // Remove the account
    AccountManager accountManager = AccountManager.get(context);
    accountManager.removeAccount(account, null, null);

    // Clear user data
    // ...
}
