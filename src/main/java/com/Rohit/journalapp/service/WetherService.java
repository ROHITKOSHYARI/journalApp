package com.Rohit.journalapp.service;

import com.Rohit.journalapp.api.response.WetherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WetherService {
    private static final String apikey = "7a007a6dfcc4c915da7bae867f646a66";

    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY_NAME";

    @Autowired
    private RestTemplate restTemplate;

    public WetherResponse getWether(String city){
        String url = API.replace("API_KEY",apikey).replace("CITY_NAME",city);
        ResponseEntity<WetherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WetherResponse.class);
        return response.getBody();
    }
}
