package com.Rohit.journalapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void emailtest(){
        emailService.sendEmail("deepalikala04@gmail.com","Testing java email sender","testing mail ");
    }
}
