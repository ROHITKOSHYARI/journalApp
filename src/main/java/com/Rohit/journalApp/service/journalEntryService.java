package com.Rohit.journalApp.service;

import com.Rohit.journalApp.entity.JournalEntry;
import com.Rohit.journalApp.repository.journalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class journalEntryService{

    @Autowired
    private journalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }
    
    public Optional<JournalEntry> findByID(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepo.deleteById(id);
    }

    public ResponseEntity<?> updateById(ObjectId id, JournalEntry UpdateEntry){
        JournalEntry old = journalEntryRepo.findById(id).orElse(null);
        if(old != null){
            old.setTitle(UpdateEntry.getTitle() != null && !UpdateEntry.getTitle().equals("") ? UpdateEntry.getTitle() : old.getTitle() );
            old.setContent(UpdateEntry.getContent() != null && !UpdateEntry.getContent().equals("") ? UpdateEntry.getContent() : old.getContent() );
            journalEntryRepo.save(old);
            return new ResponseEntity<>(old,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}