/*RestTemplate restTemplate = new RestTemplate();

String url = "https://ec2-13-232-61-19.ap-south-1.compute.amazonaws.com/oauth2/default/login";

HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);

// create the request entity
HttpEntity<String> requestEntity = new HttpEntity<>(headers);

// execute the request and get the response
ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

// print the response body
System.out.println(response.getBody());

*/

//


/*
RestTemplate restTemplate = new RestTemplate();
String url = "https://example.com/api/endpoint";

// execute the request and get the response
ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

if (response.getStatusCode().is2xxSuccessful()) {
    // server response is successful
    String responseBody = response.getBody();
    // do something with the response body
} else {
    // server returned an error status code
    HttpStatus statusCode = response.getStatusCode();
    String errorMessage = String.format("Request failed with status code %d - %s",
            statusCode.value(), statusCode.getReasonPhrase());
    // handle the error
}

*/


//


/* 
@Bean
public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    // configure the RestTemplate
    return restTemplate;
}



public class AuthResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;

    // getter and setter methods
}



String url = "https://ec2-13-232-61-19.ap-south-1.compute.amazonaws.com/oauth2/default/login";

HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
headers.setBasicAuth("client_id", "client_secret");

MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
body.add("grant_type", "password");
body.add("username", "user@example.com");
body.add("password", "password");

AuthResponse response = restTemplate.postForObject(url, new HttpEntity<>(body, headers), AuthResponse.class);
*/

//

/*
import java.nio.charset.StandardCharsets;
import java.util.Base64;

// ...

WebClient webClient = WebClient.builder()
    .baseUrl("https://ec2-13-232-61-19.ap-south-1.compute.amazonaws.com/oauth2/default/login")
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("client_id:client_secret".getBytes(StandardCharsets.UTF_8)))
    .build();

MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
formData.add("grant_type", "password");
formData.add("username", "user@example.com");
formData.add("password", "password");

AuthResponse response = webClient.post()
    .body(BodyInserters.fromFormData(formData))
    .retrieve()
    .bodyToMono(AuthResponse.class)
    .block();

    if (response.getAccess_token() != null) {
        // authentication successful
        String accessToken = response.getAccess_token();
        // use the access token to make authenticated requests to the API
    } else {
        // authentication failed
        String errorMessage = response.getError_description();
        // handle the error message
    }

    */


    ////

    /*
     public class App {

private static RestTemplate client=getRestTemplate();

    private static int DEFAULT_PORT = 8080;

private static String DEFAULT_HOST = "localhost";

private static int port=DEFAULT_PORT;

private static String hostName = DEFAULT_HOST;


 public static  void main(String[] args) throws IOException {
    try {
        testHappyDayWithForm();
    } catch (Exception ex) {
        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
    }
}


public static void testHappyDayWithForm() throws Exception {

    MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
    formData.add("grant_type", "password");
    formData.add("client_id", "my-trusted-client");
    formData.add("scope", "read");
    formData.add("username", "muhammed");
    formData.add("password", "1234");

    ResponseEntity<String> response = postForString("/sparklr/oauth/token", formData);
    System.out.println( response.getStatusCode());
    System.out.println(response.getHeaders().getFirst("Cache-Control"));

    DefaultOAuth2SerializationService serializationService = new DefaultOAuth2SerializationService();
    OAuth2AccessToken accessToken = serializationService.deserializeJsonAccessToken(new ByteArrayInputStream(
            response.getBody().getBytes()));

    // now try and use the token to access a protected resource.

    // first make sure the resource is actually protected.
    //assertNotSame(HttpStatus.OK, serverRunning.getStatusCode("/sparklr/photos?format=json"));

    // now make sure an authorized request is valid.
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, accessToken.getValue()));
    //assertEquals(HttpStatus.OK, serverRunning.getStatusCode("/sparklr/photos?format=json", headers));
}

    public static ResponseEntity<String> postForString(String path, MultiValueMap<String, String> formData) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
            System.out.println(getUrl(path));
    return client.exchange(getUrl(path), HttpMethod.POST, new HttpEntity<MultiValueMap<String, String>>(formData,
            headers), String.class);
}
    public static String getUrl(String path) {
    if (!path.startsWith("/")) {
        path = "/" + path;
    }
    return "http://" + hostName + ":" + port + path;
}

    public static RestTemplate getRestTemplate() {
    RestTemplate client = new RestTemplate();
    CommonsClientHttpRequestFactory requestFactory = new CommonsClientHttpRequestFactory() {
        @Override
        protected void postProcessCommonsHttpMethod(HttpMethodBase httpMethod) {
            httpMethod.setFollowRedirects(false);
            // We don't want stateful conversations for this test
            httpMethod.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        }
    };
    client.setRequestFactory(requestFactory);
    client.setErrorHandler(new ResponseErrorHandler() {
        // Pass errors through in response entity for status code analysis
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return false;
        }

        public void handleError(ClientHttpResponse response) throws IOException {
        }
    });
    return client;
}

https://stackoverflow.com/questions/7890661/how-can-i-create-oauth-2-username-password-flow-over-spring-security


     */