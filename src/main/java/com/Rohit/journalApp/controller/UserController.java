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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @DeleteMapping("deleteuser")
    public ResponseEntity<?> deleteByUserName(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        userService.deleteByUserName(userName);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<?> findByUserName(@RequestBody UserEntry userEntry){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        UserEntry user = userService.FindByUserName(userName);
        if (user != null){
            user.setUserName(userEntry.getUserName());
            user.setPassword(userEntry.getPassword());
            userService.saveEntry(user);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
