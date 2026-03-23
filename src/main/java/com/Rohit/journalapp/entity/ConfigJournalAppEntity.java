package com.Rohit.journalapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Journal_Config")
@Data
@NoArgsConstructor
public class ConfigJournalAppEntity {

    private String key;
    private String value;

}
