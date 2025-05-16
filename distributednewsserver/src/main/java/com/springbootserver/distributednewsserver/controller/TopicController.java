package com.springbootserver.distributednewsserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import com.springbootserver.distributednewsserver.dto.TopicDto;
import com.springbootserver.distributednewsserver.service.KafkaTopicService;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private KafkaTopicService kafkaTopicService;

    @PostMapping("/create")
    public ResponseEntity<?> createTopic(@RequestBody TopicDto topic) {
        try {
            if (topic.getTopicName() == null || topic.getTopicName().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
            }
            kafkaTopicService.createTopic(topic.getTopicName(), topic.getPartitions(), topic.getReplicationFactor());
            return ResponseEntity.status(HttpStatus.CREATED).body(topic);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating topic: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listTopics() {
        try {
            List<String> topics = kafkaTopicService.getTopics();
            topics.remove("data");
            return ResponseEntity.ok(topics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error fetching topics: " + e.getMessage());
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body: " + ex.getMessage());
    }
}
