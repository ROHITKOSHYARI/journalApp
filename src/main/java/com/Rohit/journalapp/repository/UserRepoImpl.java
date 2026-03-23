package com.Rohit.journalapp.repository;

import com.Rohit.journalapp.entity.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImpl {


    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntry> queryForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));
        List<UserEntry> userEntries = mongoTemplate.find(query, UserEntry.class);
        return userEntries;
    }
}
