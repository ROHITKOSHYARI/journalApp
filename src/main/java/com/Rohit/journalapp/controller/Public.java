package com.Rohit.journalapp.controller;

import com.Rohit.journalapp.entity.UserEntry;
import com.Rohit.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class Public {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/create_user")
    public ResponseEntity<?> createUser(@RequestBody UserEntry newUser){
        try {
            userService.saveNewEntry(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
