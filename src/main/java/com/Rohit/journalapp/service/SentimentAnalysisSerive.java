package com.Rohit.journalapp.service;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisSerive {
    public String userSentiment(String str){
        return "Happy";
    }
}
