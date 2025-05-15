package com.springbootserver.distributednewsserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopicService {
    // This service is responsible for managing Kafka topics
    // You can add methods to create, delete, or list topics as needed

    @Autowired
    private KafkaAdmin kafkaAdmin;

    public void createTopic(String topicName, int partitions, short replicationFactor) {
        // Create a new topic with the specified name and configuration
        try {
            kafkaAdmin.createOrModifyTopics(
                TopicBuilder.name(topicName)
                    .partitions(partitions)
                    .replicas(replicationFactor)
                    .build()
            );
           
        } catch (Exception e) {
            throw e;
        }
    }
    

    
}
