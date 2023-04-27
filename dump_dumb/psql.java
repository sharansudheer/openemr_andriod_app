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
            String userName = secrets.RDS_USERNAME;
            String password = secrets.RDS_PASSWORD;
            String dbName = secrets.RDS_DB_NAME;
            String port = secrets.RDS_PORT;
            // Connect to the database
            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;

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