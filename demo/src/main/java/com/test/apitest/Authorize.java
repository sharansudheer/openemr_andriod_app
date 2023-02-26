package com.test.apitest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Authorize {
	public void GetAccessToken() throws JsonMappingException, JsonProcessingException {
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

    	String url = "https://mobileapp.trackdemon.in/oauth2/default/token";
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
	}
}
