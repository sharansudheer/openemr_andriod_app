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
Account account = new Account("account_name", "account_type");
AccountManager accountManager = AccountManager.get(context);
String authToken = "your_access_token";
boolean accountAdded = accountManager.addAccountExplicitly(account, authToken, null);
AccountManager accountManager = AccountManager.get(context);
Account account = new Account("account_name", "account_type");
String authTokenType = "your_auth_token_type";
accountManager.getAuthToken(account, authTokenType, null, activity, new AccountManagerCallback<Bundle>() {
    @Override
    public void run(AccountManagerFuture<Bundle> future) {
        try {
            String authToken = future.getResult().getString(AccountManager.KEY_AUTHTOKEN);
            // Use the authToken to make API requests
        } catch (OperationCanceledException | AuthenticatorException | IOException e) {
            e.printStackTrace();
        }
    }
}, null);


@Override
public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
    if (response.isSuccessful()) {
        // Store access token in Account Manager
        AccountManager accountManager = AccountManager.get(getApplicationContext());
        Account account = new Account(username, "com.example.myapp.account");
        accountManager.addAccountExplicitly(account, null, null);
        accountManager.setAuthToken(account, "access_token", response.body().getAccessToken());

        // Launch Dashboard activity
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    } else {
        Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show();
    }
}

// Check if access token is already present in Account Manager
AccountManager accountManager = AccountManager.get(getApplicationContext());
Account[] accounts = accountManager.getAccountsByType("com.example.myapp.account");
if (accounts.length > 0) {
    String accessToken = accountManager.peekAuthToken(accounts[0], "access_token");
    if (accessToken != null) {
        // Launch Dashboard activity
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        return; // Don't launch Login activity
    }
}

// Launch Login activity
Intent intent = new Intent(this, LoginActivity.class);
startActivity(intent);
