package com.springbootserver.distributednewsserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootserver.distributednewsserver.dto.SendNewsDto;
import com.springbootserver.distributednewsserver.service.NewsProducerService;

@RestController
@RequestMapping("/news")
public class NewsProducerController {
    @Autowired
    private NewsProducerService newsProducerService;
    
    @PostMapping
    public ResponseEntity<?> sendNews(@RequestBody SendNewsDto newsDto) {
        // Send the news message to the specified Kafka topic
        try{
        if (newsDto.getTopicName() == null || newsDto.getTopicName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
            newsProducerService.sendNews(newsDto.getTopicName(), newsDto.getMessage());
            return ResponseEntity.status(HttpStatus.CREATED).body(newsDto);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending news: " + e.getMessage());
        
        }
    }
    

}