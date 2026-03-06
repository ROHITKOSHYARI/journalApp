package com.Rohit.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Document(collection = "Journal")
@Data
@NoArgsConstructor
public class JournalEntry {

    private ObjectId id;

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;
}
