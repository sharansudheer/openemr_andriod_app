RestTemplate restTemplate = new RestTemplate();

String url = "https://ec2-13-232-61-19.ap-south-1.compute.amazonaws.com/oauth2/default/login";

HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);

// create the request entity
HttpEntity<String> requestEntity = new HttpEntity<>(headers);

// execute the request and get the response
ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

// print the response body
System.out.println(response.getBody());



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
