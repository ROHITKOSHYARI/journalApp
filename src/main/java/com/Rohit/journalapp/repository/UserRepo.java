package com.Rohit.journalapp.repository;

import com.Rohit.journalapp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUserName(String userName);
    void deleteByUserName(String userName);
}

