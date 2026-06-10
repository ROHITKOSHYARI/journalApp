package com.Rohit.journalapp.controller;

import com.Rohit.journalapp.entity.UserEntry;
import com.Rohit.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAll(){
        List<UserEntry> list = userService.getAll();
        if(list != null && !list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createNewAdmin(@RequestBody UserEntry userEntry){
        if (userEntry != null) {
            try {
                userService.saveNewADMIN(userEntry);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
