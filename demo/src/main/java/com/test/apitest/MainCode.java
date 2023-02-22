package com.test.apitest;

import org.HdrHistogram.AbstractHistogram;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List; 

public class MainCode {
    public static void main(String[] args) {
        try {
        	String accessToken = new Authorize().GetAccessToken();
        	RestTemplate restTemplate = new RestTemplate();
        	HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_JSON);

        	// Replace <your_bearer_token> with your actual bearer token
        	headers.setBearerAuth(accessToken);

        	JSONObject patientJson = new JSONObject();
        	patientJson.put("title", "Mr");
        	patientJson.put("fname", "Foo");
        	patientJson.put("mname", "");
        	patientJson.put("lname", "Bar");
        	patientJson.put("street", "456 Tree Lane");
        	patientJson.put("postal_code", "08642");
        	patientJson.put("city", "FooTown");
        	patientJson.put("state", "FL");
        	patientJson.put("country_code", "US");
        	patientJson.put("phone_contact", "123-456-7890");
        	patientJson.put("DOB", "1992-02-02");
        	patientJson.put("sex", "Male");
        	patientJson.put("race", "");
        	patientJson.put("ethnicity", "");

        	HttpEntity<String> entity = new HttpEntity<String>(patientJson.toString(), headers);
        	String url = "https://mobileapp.trackdemon.in/apis/default/api/patient";
        	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        	System.out.println(response.getBody());


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
        } catch (Exception ex) {
            // handle other errors
            System.out.println("Other Error: " + ex.getMessage());
        }
    }
}
