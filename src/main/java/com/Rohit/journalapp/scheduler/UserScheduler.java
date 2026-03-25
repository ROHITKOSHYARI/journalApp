package com.Rohit.journalapp.scheduler;

import com.Rohit.journalapp.Enum.Sentiment;
import com.Rohit.journalapp.entity.JournalEntry;
import com.Rohit.journalapp.entity.UserEntry;
import com.Rohit.journalapp.repository.UserRepoImpl;
import com.Rohit.journalapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepoImpl userRepo;


    @Scheduled(cron = "0 0 9 ? * SUN")
    public void fetchUserAndSendMail(){
        List<UserEntry> users = userRepo.queryForSA();
        for(UserEntry user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> list = journalEntries.stream().filter(X -> X.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(X -> X.getSentiment()).toList();
            Map<Sentiment, Integer> sentimentsCount = new HashMap<>();
            for(Sentiment sentiment : list){
                if (sentiment != null) {
                    sentimentsCount.put(sentiment, sentimentsCount.getOrDefault(sentiment,0)+1);
                }
            }
            Sentiment mostfreqSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment, Integer> entry : sentimentsCount.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostfreqSentiment = entry.getKey();
                }
            }
            if(mostfreqSentiment != null){
                emailService.sendEmail("Rohitkoshyari12@gmail.com","Your Sentiment this week","your were "+mostfreqSentiment+" this week");
            }
        }
    }
}
