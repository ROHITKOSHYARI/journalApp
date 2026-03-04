package com.Rohit.journalApp.service;

import com.Rohit.journalApp.entity.UserEntry;
import com.Rohit.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveEntry(UserEntry userEntry){
        userRepo.save(userEntry);
    }

    public List<UserEntry> getAll(){
        return userRepo.findAll();
    }

    public Optional<UserEntry> findByID(ObjectId id){
        return findByID(id);
    }

    public void deleteById(ObjectId id) {
        userRepo.deleteById(id);
    }
}