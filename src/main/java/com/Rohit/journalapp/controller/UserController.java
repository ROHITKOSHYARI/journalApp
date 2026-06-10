package com.Rohit.journalapp.controller;

import com.Rohit.journalapp.entity.UserEntry;
import com.Rohit.journalapp.service.UserService;
import com.Rohit.journalapp.service.WetherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WetherService wetherService;

    @PostMapping()
    public ResponseEntity<?> hellouser(){
        String username =  Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        int tempratere = wetherService.getWether("Dehradun").getCurrent().getFeelsLike();
        return new ResponseEntity<>("hello " + username + " wether feels like " + tempratere+" degree celcious", HttpStatus.OK);
    }

    @PostMapping("/get/{ID}")
    public ResponseEntity<?> findById(@PathVariable ObjectId ID) {
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
