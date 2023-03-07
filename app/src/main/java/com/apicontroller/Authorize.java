package com.apicontroller;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Authorize {
	public String GetAccessToken(String usrname, String passcode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", "password");
			map.add("client_id", "FTHOrCUow4SvwKhkPe7jRlLUzygTcSyzYOyUV9DTZEQ");
			map.add("scope", "openid api:oemr api:fhir user/allergy.read user/allergy.write user/appointment.read user/appointment.write user/dental_issue.read user/dental_issue.write user/document.read user/document.write user/drug.read user/encounter.read user/encounter.write user/facility.read user/facility.write user/immunization.read user/insurance.read user/insurance.write user/insurance_company.read user/insurance_company.write user/insurance_type.read user/list.read user/medical_problem.read user/medical_problem.write user/medication.read user/medication.write user/message.write user/patient.read user/patient.write user/practitioner.read user/practitioner.write user/prescription.read user/procedure.read user/soap_note.read user/soap_note.write user/surgery.read user/surgery.write user/transaction.read user/transaction.write user/vital.read user/vital.write");
			map.add("user_role", "users");
			map.add("username", "usrname");
			map.add("password", "passcode");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			String url = "https://mobileapp.trackdemon.in/oauth2/default/token";
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
			//String accessToken = jsonNode.get("access_token").asText();
			// String accessToken = response.getAccessToken();
			return jsonNode.get("access_token").asText();

		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				// handle unauthorized request
				throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
			} else if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
				// handle bad request
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
			} else {
				// handle other client errors
				return "UNAUTHORIZED, 401";
			}
		} catch (Exception ex) {
			// handle other errors
			return "Error" + ex;
		}
	}
}
