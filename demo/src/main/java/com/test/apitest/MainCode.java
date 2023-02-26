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
        try { /*
        	String accessToken = new Authorize().GetAccessToken();
        	RestTemplate restTemplate = new RestTemplate();
        	HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_JSON);

        	System.out.println("the Access is " + accessToken);
        	
        	// Replace  with your actual bearer token
        	headers.setBearerAuth(accessToken);
        	*/
/*
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
        	
*/
        	RestTemplate restTemplate = new RestTemplate();

        	HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_JSON);

        	MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        	map.add("application_type", "private");
        	map.add("redirect_uris", "https://mobileapp.trackdemon.in/callback");
        	map.add("client_name", "main_api_tester");
        	map.add("token_endpoint_auth_method", "client_secret_post");
        	map.add("scope", "openid offline_access api:oemr api:fhir api:port user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user/dental_issue.write user/document.read user/document.write user/drug.read user/encounter.read user/encounter.write user/facility.read user/facility.write user/immunization.read user/insurance.read user/insurance.write user/insurance_company.read user/insurance_company.write user/insurance_type.read user/list.read user/medical_problem.read user/medical_problem.write user/medication.read user/medication.write user/message.write user/patient.read user/patient.write user/practitioner.read user/practitioner.write user/prescription.read user/procedure.read user/soap_note.read user/soap_note.write user/surgery.read user/surgery.write user/transaction.read user/transaction.write user/vital.read user/vital.write user/AllergyIntolerance.read user/CareTeam.read user/Condition.read user/Coverage.read user/Encounter.read user/Immunization.read user/Location.read user/Medication.read user/MedicationRequest.read user/Observation.read user/Organization.read user/Organization.write user/Patient.read user/Patient.write user/Practitioner.read user/Practitioner.write user/PractitionerRole.read user/Procedure.read patient/encounter.read patient/patient.read patient/AllergyIntolerance.read patient/CareTeam.read patient/Condition.read patient/Coverage.read patient/Encounter.read patient/Immunization.read patient/MedicationRequest.read patient/Observation.read patient/Patient.read patient/Procedure.read");
        	
        	HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        	String url = "https://mobileapp.trackdemon.in/oauth2/default/registration";
        	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

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


