package com.Rohit.journalApp.controller;

import com.Rohit.journalApp.JournalApplication;
import com.Rohit.journalApp.entity.JournalEntry;
import com.Rohit.journalApp.service.journalEntryService;
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

    @GetMapping
    public ResponseEntity<?> getall() {
        List<JournalEntry> all = journalEntryService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createEntries(@RequestBody JournalEntry myRequest) {
        try {
            journalEntryService.setDate(myRequest);
            journalEntryService.saveEntry(myRequest);
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

    @DeleteMapping("/ID/{getID}")
    public ResponseEntity<?> deleteByID(@PathVariable ObjectId getID) {
        journalEntryService.deleteById(getID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/Update/{getID}")
    public ResponseEntity<?> updateByID(@PathVariable ObjectId getID, @RequestBody JournalEntry myRequest) {
        return journalEntryService.updateById(getID , myRequest);
    }
}
