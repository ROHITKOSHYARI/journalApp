package com.Rohit.journalApp.service;

import com.Rohit.journalApp.entity.UserEntry;
import com.Rohit.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(UserEntry userEntry){
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
//        fixed size array
        userEntry.setRoles(List.of("USER"));
        userRepo.save(userEntry);
    }

    public List<UserEntry> getAll(){
        return userRepo.findAll();
    }

    public Optional<UserEntry> findByID(ObjectId id){
        return findByID(id);
    }

    public void deleteByUserName(String userName){
        userRepo.deleteByUserName(userName);
    }

    public void deleteById(ObjectId id) {
        userRepo.deleteById(id);
    }
    public UserEntry FindByUserName(String userName){
        return userRepo.findByUserName(userName);
    }
}