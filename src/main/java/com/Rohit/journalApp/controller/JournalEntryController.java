package com.Rohit.journalApp.controller;

import com.Rohit.journalApp.JournalApplication;
import com.Rohit.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    Map<Long , JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getall(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntries(@RequestBody JournalEntry myRequest){
        journalEntries.put(myRequest.getId(),myRequest);
        return true;
    }

    @GetMapping("/ID/{getID}")
    public JournalEntry getID(@PathVariable Long getID){
        return journalEntries.get(getID);
    }

    @DeleteMapping("/ID/{getID}")
    public boolean deleteID(@PathVariable Long getID){
        journalEntries.remove(getID);
        return true;
    }

    @PutMapping("/Update/{getID}")
    public boolean updateByID(@PathVariable Long getID , @RequestBody JournalEntry myRequest){
        journalEntries.put(getID,myRequest);
        return true;
    }
}
