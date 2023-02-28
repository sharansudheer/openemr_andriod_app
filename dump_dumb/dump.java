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

/*
 ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.GET, null, String.class);


 */

/*

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






     //MAIN CODE
     //MAIN CODE

     // Test of Main Data Transfer, POST Request

     /*
      * 

RestTemplate restTemplate = new RestTemplate();
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

Map<String, Object> requestBody = new LinkedHashMap<>();
requestBody.put("title", "Mr");
requestBody.put("fname", "Foo");
requestBody.put("mname", "");
requestBody.put("lname", "Bar");
requestBody.put("street", "456 Tree Lane");
requestBody.put("postal_code", "08642");
requestBody.put("city", "FooTown");
requestBody.put("state", "FL");
requestBody.put("country_code", "US");
requestBody.put("phone_contact", "123-456-7890");
requestBody.put("DOB", "1992-02-02");
requestBody.put("sex", "Male");
requestBody.put("race", "");
requestBody.put("ethnicity", "");

HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
URI endpoint = URI.create("https://four.openemr.io/a/openemr/apis/default/api/patient");
ResponseEntity<String> response = restTemplate.postForEntity(endpoint, entity, String.class);
System.out.println(response.getBody());
      */




      /*
       You can create a FormData object and use its add method to add the key-value pair for "Scope" and its value. Since the value has special characters such as space and '%', 
       you should encode the value using URL encoding. Here is an example code snippet:
       
       Note that you need to 
       import java.net.URLEncoder and java.nio.charset.StandardCharsets.
       */
/* 
      FormData formData = new FormData();
String scopeValue = "openid offline_access api:port api:fhir patient/encounter.read patient/patient.read patient/AllergyIntolerance.read patient/CareTeam.read patient/Condition.read patient/Coverage.read patient/Encounter.read patient/Immunization.read patient/Medication.read patient/MedicationRequest.read patient/Observation.read patient/Patient.read patient/Procedure.read";
formData.add("Scope", URLEncoder.encode(scopeValue, StandardCharsets.UTF_8));

*/

/*
grant_type: Its value is password. This field indicates the grant type of the authentication request. Here, it is password grant, which is used for authenticating users with a username and password.
client_id: Its value is my-trusted-client. This field represents the client ID that the authorization server issues to the client.
scope: Its value is read. This field represents the scope of the access request. Here, the scope is read, which means that the client is requesting read-only access to a protected resource.
username: Its value is muhammed. This field represents the username of the user who is trying to authenticate.
password: Its value is 1234. This field represents the password of the user who is trying to authenticate.

*/
 
/*
const formData = new FormData();
formData.append("Scope", "openid offline_access api:oemr api:fhir user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user/dental_issue.write user/document.read user/document.write user/drug.read user/encounter.read user/encounter.write user/facility.read user/facility.write user/immunization.read user/insurance.read user/insurance.write user/insurance_company.read user/insurance_company.write user/insurance_type.read user/list.read user/medical_problem.read user/medical_problem.write user/medication.read user/medication.write user/message.write user/patient.read user/patient.write user/practitioner.read user/practitioner.write user/prescription.read user/procedure.read user/soap_note.read user/soap_note.write user/surgery.read user/surgery.write user/transaction.read user/transaction.write user/vital.read user/vital.write user/AllergyIntolerance.read user/CareTeam.read user/Condition.read user/Coverage.read user/Encounter.read user/Immunization.read user/Location.read user/Medication.read user/MedicationRequest.read user/Observation.read user/Organization.read user/Organization.write user/Patient.read user/Patient.write user/Practitioner.read user/Practitioner.write user/PractitionerRole.read user/Procedure.read");
*/



/*
 String scopeValue = "openid offline_access" + 
                    "api:oemr api:fhir" +
                    "user/allergy.read" +
                    "user/allergy.write" +
                    "user/appointment.read" +
                    "user/appointment.write" +
                    "user/dental_issue.read" +
                    "user/dental_issue.write" +
                    "user/document.read" +
                    "user/document.write" +
                    "user/drug.read" +
                    "user/encounter.read" +
                    "user/encounter.write" +
                    "user/facility.read" +
                    "user/facility.write" +
                    "user/immunization.read" +
                    "user/insurance.read" +
                    "user/insurance.write" +
                    "user/insurance_company.read" + 
                    "user/insurance_company.write"  + 
                    "user/insurance_type.read" +
                    "user/list.read" + 
                    "user/medical_problem.read" + 
                    "user/medical_problem.write" +
                    "user/medication.read" + 
                    "user/medication.write" + 
                    "user/message.write" +
                    "user/patient.read" + 
                    "user/patient.write" + 
                    "user/practitioner.read" +
                    "user/practitioner.write" +
                    "user/prescription.read" +
                    "user/procedure.read" +
                    "user/soap_note.read" + 
                    "user/soap_note.write" + 
                    "user/surgery.read" +
                    "user/surgery.write" +
                    "user/transaction.read" + 
                    "user/transaction.write" +
                    "user/vital.read" + 
                    "user/vital.write" +
                    "user/AllergyIntolerance.read" +
                    "user/CareTeam.read" + 
                    "user/Condition.read"+ 
                    "user/Coverage.read" +
                    "user/Encounter.read" +
                    "user/Immunization.read" + 
                    "user/Location.read" +
                    "user/Medication.read" +
                    "user/MedicationRequest.read" +
                    "user/Observation.read" +
                    "user/Organization.read" +
                    "user/Organization.write" +
                    "user/Patient.read" +
                    "user/Patient.write" +
                    "user/Practitioner.read" +
                    "user/Practitioner.write" +
                    "user/PractitionerRole.read" +
                    "user/Procedure.read";

 */

 /*
  public class Authorize {
	public String GetAccessToken() throws JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();

    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    	MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
    	map.add("grant_type", "password");
    	map.add("client_id", "NNyB7CqS7df5Id16QY4H_27VJUk9zjQU2M1zVmDJ2Lo");
    	map.add("scope", "openid api:oemr api:fhir user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user/dental_issue.write user/document.read user/document.write user/drug.read user/encounter.read user/encounter.write user/facility.read user/facility.write user/immunization.read user/insurance.read user/insurance.write user/insurance_company.read user/insurance_company.write user/insurance_type.read user/list.read user/medical_problem.read user/medical_problem.write user/medication.read user/medication.write user/message.write user/patient.read user/patient.write user/practitioner.read user/practitioner.write user/prescription.read user/procedure.read user/soap_note.read user/soap_note.write user/surgery.read user/surgery.write user/transaction.read user/transaction.write user/vital.read user/vital.write");
    	map.add("user_role", "users");
    	map.add("username", "test");
    	map.add("password", "123456789");

    	HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

    	String url = "https://mobileapp.trackdemon.in/oauth2/default/token";
    	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

    	JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
    	String accessToken = jsonNode.get("access_token").asText();


        // String accessToken = response.getAccessToken();

        System.out.println(accessToken);

    	return accessToken;
	}
}
JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        	String clientId = jsonNode.get("client_id").asText();
        	String clientSecret = jsonNode.get("client_secret").asText();
        	String regisToken =jsonNode.get("registration_access_token").asText();
        	String clientName=jsonNode.get("client_name").asText();
        	String tokenEnd = jsonNode.get("token_endpoint_auth_method").asText(); 
        	String scope = jsonNode.get("scope").asText();

            // String accessToken = response.getAccessToken();

            System.out.println("Client Id:- " + clientId);
            System.out.println("Client Secret:- " + clientSecret);
            System.out.println("Registration Token:- " + regisToken);
            System.out.println("Client Name" + clientName);
            System.out.println("token End point auth" + tokenEnd);
            System.out.println("scope:- " + scope);

  */

 /*
  
    RestTemplate restTemplate = new RestTemplate();

        	HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_JSON);

        	
        	  String requestBody = "{\"application_type\": \"private\",\"redirect_uris\": [\"https://mobileapp.trackdemon.in/callback\"],\"client_name\": \"main_api_test\",\"token_endpoint_auth_method\": \"client_secret_post\",\"scope\": \"openid offline_access api:oemr api:fhir api:port user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user/dental_issue.write user/document.read user/document.write user/drug.read user/encounter.read user/encounter.write user/facility.read user/facility.write user/immunization.read user/insurance.read user/insurance.write user/insurance_company.read user/insurance_company.write user/insurance_type.read user/list.read user/medical_problem.read user/medical_problem.write user/medication.read user/medication.write user/message.write user/patient.read user/patient.write user/practitioner.read user/practitioner.write user/prescription.read user/procedure.read user/soap_note.read user/soap_note.write user/surgery.read user/surgery.write user/transaction.read user/transaction.write user/vital.read user/vital.write user/AllergyIntolerance.read user/CareTeam.read user/Condition.read user/Coverage.read user/Encounter.read user/Immunization.read user/Location.read user/Medication.read user/MedicationRequest.read user/Observation.read user/Organization.read user/Organization.write user/Patient.read user/Patient.write user/Practitioner.read user/Practitioner.write user/PractitionerRole.read user/Procedure.read patient/encounter.read patient/patient.read patient/AllergyIntolerance.read patient/CareTeam.read patient/Condition.read patient/Coverage.read patient/Encounter.read patient/Immunization.read patient/MedicationRequest.read patient/Observation.read patient/Patient.read patient/Procedure.read\"}";
        	
        	
        	

        	String url = "https://mobileapp.trackdemon.in/oauth2/default/registration";
        	HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        	ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        	System.out.println(responseEntity.getBody());
test  dump
  */