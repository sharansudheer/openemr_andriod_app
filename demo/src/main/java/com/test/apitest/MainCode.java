package com.test.apitest;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class MainCode {
	public static void main(String[] args) {
		try {
		    // make the API request
			WebClient webClient = WebClient.builder()
				    .baseUrl("https://ec2-13-232-61-19.ap-south-1.compute.amazonaws.com/oauth2/default/login")
				    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				    .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("client_id:client_secret".getBytes(StandardCharsets.UTF_8)))
				    .build();
			MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
		    formData.add("grant_type", "password");
		    formData.add("client_id", "my-trusted-client");
		    formData.add("scope", "read");
		    formData.add("username", "muhammed");
		    formData.add("password", "1234");
		    /*
		         webClient.post(): This creates a POST request using the WebClient object named webClient.
    .body(BodyInserters.fromFormData(formData)): This sets the request body as form data. The formData object is converted to a MultiValueMap using the LinkedMultiValueMap implementation provided by Spring.
    .retrieve(): This sends the request to the server and retrieves the server response as a BodyExtractor object.
    .bodyToMono(AuthResponse.class): This parses the response body as an AuthResponse object. The Mono class is used to represent an asynchronous computation that will eventually produce a single result.
    .block(): This blocks the current thread until the response is received. The block() method is called on the Mono object returned by bodyToMono(), which waits for the response to be received before returning the AuthResponse object.

In summary, the code sends a POST request to the server with the form data specified in the formData object, retrieves the server response as an AuthResponse object using the WebClient, and blocks the current thread until the response is received.
		     */
		    AuthResponse response = webClient.post()
		    	    .body(BodyInserters.fromFormData(formData))
		    	    .retrieve()
		    	    .bodyToMono(AuthResponse.class)
		    	    .block();
		    String accessToken = response.getAccessToken();
		    System.out.println(accessToken);
		} catch (HttpClientErrorException ex) {
		    if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
		        // handle unauthorized request
		    	System.out.println("UNAUTHORIZED, 401");
		    } else if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
		        // handle bad request
		    	System.out.println("BAD_REQUEST, 400");
		    } else {
		        // handle other client errors
		    	System.out.println("Other Client Error, 4--");
		    }
		} catch (HttpServerErrorException ex) {
		    if (ex.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
		        // handle internal server error
		    	System.out.println("INTERNAL_SERVER_ERROR, 500");
		    } else {
		        // handle other server errors
		    	System.out.println("Other server errors, 5--");
		    }
		}
	
	

	
	
	RestTemplate restTemplate = new RestTemplate();
	String url = "https://ec2-13-232-61-19.ap-south-1.compute.amazonaws.com/oauth2/default/login";

	// execute the request and get the response
	
	ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

	// get the response body as a String
	String responseBody = responseEntity.getBody();

	// print the response body
	System.out.println(responseBody);

	}
}

