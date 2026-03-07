package com.Rohit.journalApp.repository;

import com.Rohit.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUserName(String userName);
    void deleteByUserName(String userName);
}

