package com.Rohit.journalapp.service;

import com.Rohit.journalapp.api.response.WetherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WetherService {

    @Value("${Weather.api.key}")
    private  String apikey;

    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY_NAME";

    @Autowired
    private RestTemplate restTemplate;

    public WetherResponse getWether(String city){
        String url = API.replace("API_KEY",apikey).replace("CITY_NAME",city);
        ResponseEntity<WetherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WetherResponse.class);
        return response.getBody();
    }
}
