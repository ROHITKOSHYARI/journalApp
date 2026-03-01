package com.Rohit.journalApp.controller;

import com.Rohit.journalApp.JournalApplication;
import com.Rohit.journalApp.entity.JournalEntry;
import com.Rohit.journalApp.service.journalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private journalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getall() {
        return null;
    }

    @PostMapping
    public boolean createEntries(@RequestBody JournalEntry myRequest) {
        journalEntryService.saveEntry(myRequest);
        return true;
    }

    @GetMapping("/ID/{getID}")
    public JournalEntry getID(@PathVariable Long getID) {

        return null;
    }

    @DeleteMapping("/ID/{getID}")
    public boolean deleteID(@PathVariable Long getID) {

        return true;
    }

    @PutMapping("/Update/{getID}")
    public boolean updateByID(@PathVariable Long getID, @RequestBody JournalEntry myRequest) {
        return true;
    }
}
