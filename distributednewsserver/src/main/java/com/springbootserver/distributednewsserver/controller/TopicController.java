package com.springbootserver.distributednewsserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootserver.distributednewsserver.dto.TopicDto;
import com.springbootserver.distributednewsserver.service.KafkaTopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private KafkaTopicService kafkaTopicService;
    // This controller can be used to manage Kafka topics

    @PostMapping("/create")
    public ResponseEntity<?> createTopic(@RequestBody TopicDto topic ) {
        // Create a new topic with the specified name and configuration
        try{
        if (topic.getTopicName() == null || topic.getTopicName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }
        kafkaTopicService.createTopic(topic.getTopicName(), topic.getPartitions(), topic.getReplicationFactor());
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(topic);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // Handle the exception and return a custom error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body: " + ex.getMessage());
    }
    
}
