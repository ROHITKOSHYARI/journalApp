package com.Rohit.journalApp.repository;

import com.Rohit.journalApp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface journalEntryRepo extends MongoRepository<JournalEntry,Long> {

}
