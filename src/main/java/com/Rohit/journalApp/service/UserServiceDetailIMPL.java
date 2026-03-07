package com.Rohit.journalApp.service;

import com.Rohit.journalApp.entity.UserEntry;
import com.Rohit.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserServiceDetailIMPL implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry User = userRepo.findByUserName(username);
        if(User != null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(User.getUserName())
                    .password(User.getPassword())
                    .roles(User.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);


    }
}
