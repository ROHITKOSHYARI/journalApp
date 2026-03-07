package com.Rohit.journalApp.controller;

import com.Rohit.journalApp.entity.UserEntry;
import com.Rohit.journalApp.service.UserService;
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
            userService.saveEntry(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

}
