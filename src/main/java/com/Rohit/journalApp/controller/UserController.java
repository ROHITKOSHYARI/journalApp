package com.Rohit.journalApp.controller;

import com.Rohit.journalApp.entity.JournalEntry;
import com.Rohit.journalApp.entity.UserEntry;
import com.Rohit.journalApp.service.UserService;
import com.Rohit.journalApp.service.journalEntryService;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<UserEntry> list = userService.getAll();
        if(list != null && !list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntry newUser){
        try {
            userService.saveEntry(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/get/{ID}")
    public ResponseEntity<?> post(@PathVariable ObjectId ID) {
        Optional<UserEntry> user = userService.findByID(ID);
        if(user.isPresent()){
            return new ResponseEntity<>(user , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId ID){
        userService.deleteById(ID);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateuser/{userName}")
    public ResponseEntity<?> findByUserName(@RequestBody UserEntry userEntry , @PathVariable String userName){
        UserEntry user = userService.FindByUserName(userName);
        if (user != null){
            user.setUserName(userEntry.getUserName());
            user.setPassword(userEntry.getPassword());
            userService.saveEntry(user);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
