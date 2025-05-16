package com.springbootserver.distributednewsserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootserver.distributednewsserver.dto.SendNewsDto;
import com.springbootserver.distributednewsserver.service.DataToNewsProcessorService;
import com.springbootserver.distributednewsserver.service.NewsProducerService;

@RestController
@RequestMapping("/news")
public class NewsProducerController {
    @Autowired
    private NewsProducerService newsProducerService;
    @Autowired
    private DataToNewsProcessorService dataToNewsProcessorService;
    
    @PostMapping //if no topic is specified, it will be sent to processData
    public ResponseEntity<?> sendNews(@RequestBody String anyData) {
        // Send the news message to the specified Kafka topic
        try{
        if (anyData == null || anyData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }   
            SendNewsDto processedNews = dataToNewsProcessorService.processData(anyData).get();
            newsProducerService.sendNews(processedNews.getTopicName(), processedNews.getMessage());
            return ResponseEntity.status(HttpStatus.CREATED).body(processedNews);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending news: " + e.getMessage());
        
        }
    }    

}