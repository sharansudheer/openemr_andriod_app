package com.test.apitest;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



public class Template {
	@Bean
	public RestTemplate restTemplate() {
	    RestTemplate restTemplate = new RestTemplate();
	    // configure the RestTemplate
	    return restTemplate;
	}

}
