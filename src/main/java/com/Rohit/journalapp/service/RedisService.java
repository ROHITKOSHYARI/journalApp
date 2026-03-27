package com.Rohit.journalapp.service;

import com.Rohit.journalapp.api.response.WetherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> responseEntity){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if(o == null){
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(), responseEntity);
        } catch (JacksonException e) {
            log.error("Exception",e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttl){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, json, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Excecption",e);
        }
    }
}
