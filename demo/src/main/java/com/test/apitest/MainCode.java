package com.test.apitest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class MainCode {
	public static void main(String[] args) {
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

	AuthResponse response = webClient.post()
	    .body(BodyInserters.fromFormData(formData))
	    .retrieve()
	    .bodyToMono(AuthResponse.class)
	    .block();
	
	RestTemplate restTemplate = new RestTemplate();
	String url = "https://ec2-13-232-61-19.ap-south-1.compute.amazonaws.com/oauth2/default/login";

	// execute the request and get the response
	ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

	if (((Object) response.getStatusCode()).is200Successful()) {
	    // server response is successful
	    String responseBody = response2.getBody();
	    // do something with the response body
	} else {
	    // server returned an error status code
	    HttpStatus statusCode = response2.getStatusCode();
	    String errorMessage = String.format("Request failed with status code %d - %s",
	            statusCode.value(), statusCode.getReasonPhrase());
	    // handle the error
		}
	}
}

