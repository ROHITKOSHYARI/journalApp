package com.Rohit.journalapp.scheduler;

import com.Rohit.journalapp.entity.JournalEntry;
import com.Rohit.journalapp.entity.UserEntry;
import com.Rohit.journalapp.repository.UserRepo;
import com.Rohit.journalapp.repository.UserRepoImpl;
import com.Rohit.journalapp.service.EmailService;
import com.Rohit.journalapp.service.SentimentAnalysisSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepoImpl userRepo;

    @Autowired
    SentimentAnalysisSerive sentimentAnalysisSerive;

    @Scheduled(cron = "0 0 9 ? * SUN")
    public void fetchUserAndSendMail(){
        List<UserEntry> users = userRepo.queryForSA();
        for(UserEntry user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(X -> X.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(X-> X.getContent()).toList();
            String entry = String.join(" ",filteredEntries);
            String sentiment = sentimentAnalysisSerive.userSentiment(entry);
            emailService.sendEmail("Rohitkoshyari12@gmail.com", "Your Sentiment last Week", "You were " + sentiment +" this week");
        }
    }
}
