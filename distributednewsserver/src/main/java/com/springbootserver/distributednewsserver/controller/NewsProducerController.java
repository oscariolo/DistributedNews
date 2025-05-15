package com.springbootserver.distributednewsserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootserver.distributednewsserver.service.NewsProducerService;

@RestController
@RequestMapping("/news")
public class NewsProducerController {
    @Autowired
    private NewsProducerService newsProducerService;
    
    @PostMapping
    public ResponseEntity<String> sendNews(@RequestBody String message) {
        // Send the news message to the specified Kafka topic
        newsProducerService.sendNews("default-topic", message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    

}