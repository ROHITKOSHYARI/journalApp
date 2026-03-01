package com.Rohit.journalApp.service;

import com.Rohit.journalApp.entity.JournalEntry;
import com.Rohit.journalApp.repository.journalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class journalEntryService{

    @Autowired
    private journalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

}
