package com.Rohit.journalapp.service;

import com.Rohit.journalapp.entity.UserEntry;
import com.Rohit.journalapp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(UserEntry userEntry){
        userRepo.save(userEntry);
    }

    public void saveNewEntry(UserEntry userEntry){
        try {
            userEntry.setPassword(Objects.requireNonNull(passwordEncoder.encode(userEntry.getPassword())));
//        fixed size array
            userEntry.setRoles(List.of("USER"));
            userRepo.save(userEntry);
        } catch (Exception e) {
//            log.error("error occured for {} : ",userEntry.getUserName(),e);
            log.error("asdf");
            throw new RuntimeException(e);
        }
    }

    public void saveNewADMIN(UserEntry userEntry){
        userEntry.setPassword(Objects.requireNonNull(passwordEncoder.encode(userEntry.getPassword())));
        userEntry.setRoles(List.of("USER","ADMIN"));
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