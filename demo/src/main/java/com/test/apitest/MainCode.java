package com.test.apitest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MainCode {
    public static void main(String[] args) {
        try {
        	RestTemplate restTemplate = new RestTemplate();

        	HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        	MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        	map.add("grant_type", "password");
        	map.add("client_id", "NNyB7CqS7df5Id16QY4H_27VJUk9zjQU2M1zVmDJ2Lo");
        	map.add("scope", "openid offline_access api:oemr api:fhir user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user/dental_issue.write user/document.read user/document.write user/drug.read user/encounter.read user/encounter.write user/facility.read user/facility.write user/immunization.read user/insurance.read user/insurance.write user/insurance_company.read user/insurance_company.write user/insurance_type.read user/list.read user/medical_problem.read user/medical_problem.write user/medication.read user/medication.write user/message.write user/patient.read user/patient.write user/practitioner.read user/practitioner.write user/prescription.read user/procedure.read user/soap_note.read user/soap_note.write user/surgery.read user/surgery.write user/transaction.read user/transaction.write user/vital.read user/vital.write user/AllergyIntolerance.read user/CareTeam.read user/Condition.read user/Coverage.read user/Encounter.read user/Immunization.read user/Location.read user/Medication.read user/MedicationRequest.read user/Observation.read user/Organization.read user/Organization.write user/Patient.read user/Patient.write user/Practitioner.read user/Practitioner.write user/PractitionerRole.read user/Procedure.read");
        	map.add("user_role", "users");
        	map.add("username", "admin");
        	map.add("password", "i-0006671ac595ee909");

        	HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        	String url = "https://localhost:9300/oauth2/default/token";
        	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        	System.out.println(response.getBody());
            // make the API request
            WebClient webClient = WebClient.builder()
                    .baseUrl("https://mobileapp.trackdemon.in/oauth2/default/login")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("client_id:client_secret".getBytes(StandardCharsets.UTF_8)))
                    .build();
            String scopeValue = "user/patient.read";

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
            formData.add("grant_type", "password");
            formData.add("client_id", "NNyB7CqS7df5Id16QY4H_27VJUk9zjQU2M1zVmDJ2Lo");
            formData.add("user_role", "users");
            formData.add("scope", scopeValue);
            formData.add("username", "admin");
            formData.add("password", "i-0006671ac595ee909");

            for (String key : formData.keySet()) {
                List<String> values = formData.get(key);
                List<String> encodedValues = new ArrayList<>();
                for (String value : values) {
                    encodedValues.add(UriUtils.encode(value, StandardCharsets.UTF_8));
                }
                formData.put(key, encodedValues);
            }

            AuthResponse response = webClient.post()
            	    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE) // add accept header
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
        } catch (Exception ex) {
            // handle other errors
            System.out.println("Other Error: " + ex.getMessage());
        }
    }
}
