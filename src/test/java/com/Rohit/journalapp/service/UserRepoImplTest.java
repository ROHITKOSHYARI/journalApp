package com.Rohit.journalapp.service;

import com.Rohit.journalapp.entity.UserEntry;
import com.Rohit.journalapp.repository.UserRepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserRepoImplTest {

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Test
    void testSaveNewUser(){
        assertNotNull(userRepoImpl.queryForSA());
    }

}
