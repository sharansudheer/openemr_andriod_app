package com.test.apitest;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class MainCode {
    public static void main(String[] args) {
        try { 
        	        	

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


