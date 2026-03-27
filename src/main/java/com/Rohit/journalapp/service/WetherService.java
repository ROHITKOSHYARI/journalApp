package com.Rohit.journalapp.service;

import com.Rohit.journalapp.api.response.WetherResponse;
import com.Rohit.journalapp.cache.AppCache;
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

    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY_NAME";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    RedisService redisService;

    public WetherResponse getWether(String city){
        WetherResponse wetherResponse = redisService.get("Weather_of_" + city, WetherResponse.class);
        if(wetherResponse != null){
            return wetherResponse;
        }
        else{
            String url = appCache.APP_CACHE.get("weather_key").replace("API_KEY",apikey).replace("CITY_NAME",city);
            ResponseEntity<WetherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WetherResponse.class);
            WetherResponse body = response.getBody();
            redisService.set("Weather_of_"+city, body, 300L);
            return body;
        }
    }
}