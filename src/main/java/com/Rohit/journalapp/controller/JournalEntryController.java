package com.Rohit.journalapp.controller;

import com.Rohit.journalapp.entity.JournalEntry;
import com.Rohit.journalapp.entity.UserEntry;
import com.Rohit.journalapp.service.UserService;
import com.Rohit.journalapp.service.journalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private journalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> GetAllJournalEntriesOfUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry userEntry = userService.FindByUserName(userName);
        List<JournalEntry> all = userEntry.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createEntries(@RequestBody JournalEntry myRequest ) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry userEntry = userService.FindByUserName(userName);

        List<JournalEntry> collection = userEntry.getJournalEntries().stream().filter(x->x.getId().equals(getID)).toList();
        if(!collection.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findByID(getID);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteID/{getID}")
    public ResponseEntity<?> deleteByIDAndUserName(@PathVariable ObjectId getID) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<JournalEntry> collection = userService.FindByUserName(userName).getJournalEntries().stream().filter(x->x.getId().equals(getID)).toList();
        boolean removed = false;
        if(!collection.isEmpty()){
            removed =  journalEntryService.deleteById(getID , userName);
        }
        if(removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/Update/{getID}")
    @Transactional
    public ResponseEntity<?> updateByID(@PathVariable ObjectId getID, @RequestBody JournalEntry myRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        List<JournalEntry> collection = userService.FindByUserName(name).getJournalEntries().stream().filter(x->x.getId().equals(getID)).toList();

        if(!collection.isEmpty()){

            JournalEntry old = journalEntryService.findByID(getID).orElse(null);

            if(old != null){
                old.setTitle(!myRequest.getTitle().isEmpty() ?myRequest.getTitle():old.getTitle());
                old.setContent((myRequest.getContent() != null && !myRequest.getContent().isEmpty())?myRequest.getContent():old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }

        }

        return journalEntryService.updateById(getID , myRequest);

    }
}
