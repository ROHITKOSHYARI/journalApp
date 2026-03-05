package com.Rohit.journalApp.service;

import com.Rohit.journalApp.entity.JournalEntry;
import com.Rohit.journalApp.entity.UserEntry;
import com.Rohit.journalApp.repository.journalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class journalEntryService{

    @Autowired
    private journalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry , String userName){
        UserEntry userEntry = userService.FindByUserName(userName);
        JournalEntry journalEntry1 = journalEntryRepo.save(journalEntry);
        userEntry.getJournalEntries().add(journalEntry1);
        userService.saveEntry(userEntry);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }
    
    public Optional<JournalEntry> findByID(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void setDate(JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
    }

    public void deleteById(ObjectId id , String userName){
        UserEntry userEntry = userService.FindByUserName(userName);
        journalEntryRepo.deleteById(id);
        userEntry.getJournalEntries().removeIf(X -> X.getId().equals(id));
        userService.saveEntry(userEntry);
    }

    public ResponseEntity<?> updateById(ObjectId id, JournalEntry UpdateEntry){
        JournalEntry old = journalEntryRepo.findById(id).orElse(null);
        if(old != null){
            old.setTitle(!UpdateEntry.getTitle().equals("") ? UpdateEntry.getTitle() : old.getTitle());
            old.setContent(UpdateEntry.getContent() != null && !UpdateEntry.getContent().equals("") ? UpdateEntry.getContent() : old.getContent() );
            journalEntryRepo.save(old);
            return new ResponseEntity<>(old,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}