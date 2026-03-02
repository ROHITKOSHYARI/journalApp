package com.Rohit.journalApp.controller;

import com.Rohit.journalApp.JournalApplication;
import com.Rohit.journalApp.entity.JournalEntry;
import com.Rohit.journalApp.service.journalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private journalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getall() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntries(@RequestBody JournalEntry myRequest) {
        myRequest.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myRequest);
        return true;
    }

    @GetMapping("/ID/{getID}")
    public JournalEntry getByID(@PathVariable ObjectId getID) {
        return journalEntryService.findByID(getID).orElse(null);
    }

    @DeleteMapping("/ID/{getID}")
    public boolean deleteByID(@PathVariable ObjectId getID) {
        journalEntryService.deleteById(getID);
        return true;
    }

    @PutMapping("/Update/{getID}")
    public boolean updateByID(@PathVariable ObjectId getID, @RequestBody JournalEntry myRequest) {
        journalEntryService.updateById(getID , myRequest);
        return true;
    }
}
