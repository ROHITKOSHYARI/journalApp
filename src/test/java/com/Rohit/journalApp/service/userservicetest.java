package com.Rohit.journalApp.service;

import com.Rohit.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class userservicetest {


    @Autowired
    private UserRepo userRepo;

    @Test
    public void testUserService() {
//        assertEquals();
        assertNotNull(userRepo.findByUserName("rohit"));
    }
}
