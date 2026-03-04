package com.Rohit.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document
@Data
public class JournalEntry {

    private ObjectId id;

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;


}
