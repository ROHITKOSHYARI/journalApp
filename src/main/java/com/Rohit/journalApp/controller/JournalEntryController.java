package com.Rohit.journalApp.controller;

import com.Rohit.journalApp.JournalApplication;
import com.Rohit.journalApp.entity.JournalEntry;
import com.Rohit.journalApp.entity.UserEntry;
import com.Rohit.journalApp.service.UserService;
import com.Rohit.journalApp.service.journalEntryService;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private journalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> GetAllJournalEntriesOfUser(@PathVariable String userName) {
        UserEntry userEntry = userService.FindByUserName(userName);
        List<JournalEntry> all = userEntry.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> createEntries(@RequestBody JournalEntry myRequest , @PathVariable String userName) {
        try {
            journalEntryService.setDate(myRequest);
            journalEntryService.saveEntry(myRequest , userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

    }

    @GetMapping("/ID/{getID}")
    public ResponseEntity<?> getByID(@PathVariable ObjectId getID) {
        Optional<JournalEntry> journalEntry = journalEntryService.findByID(getID);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/ID/{getID}/{userName}")
    public ResponseEntity<?> deleteByIDAndUserName(@PathVariable ObjectId getID , @PathVariable String userName) {

        journalEntryService.deleteById(getID , userName);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/Update/{getID}/{userName}")
    public ResponseEntity<?> updateByID(@PathVariable ObjectId getID, @RequestBody JournalEntry myRequest , @PathVariable String userName) {

        JournalEntry old = journalEntryService.findByID(getID).orElse(null);

        if(old != null){
            old.setTitle(!myRequest.getTitle().isEmpty() ?myRequest.getTitle():old.getTitle());
            old.setContent((myRequest.getContent() != null && !myRequest.getContent().isEmpty())?myRequest.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }

        return journalEntryService.updateById(getID , myRequest);

    }
}
