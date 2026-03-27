package com.Rohit.journalapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testSaveNewUser(){
//        redisTemplate.opsForValue().set("Email","Rohit@gmail.com");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a = 1;
    }
}
