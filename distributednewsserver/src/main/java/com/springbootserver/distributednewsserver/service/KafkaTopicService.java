package com.springbootserver.distributednewsserver.service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaTopicService {

    @Autowired
    private KafkaAdmin kafkaAdmin;

    public void createTopic(String topicName, int partitions, short replicationFactor) {
        kafkaAdmin.createOrModifyTopics(
            TopicBuilder.name(topicName)
                .partitions(partitions)
                .replicas(replicationFactor)
                .build()
        );
        System.out.println("Topic created");
    }

    public List<String> getTopics() {
        Map<String, Object> config = kafkaAdmin.getConfigurationProperties();
        try (AdminClient adminClient = KafkaAdminClient.create(config)) {
            ListTopicsResult topics = adminClient.listTopics();
            return new ArrayList<>(topics.names().get()); // <- obtener los nombres de los tópicos
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error al obtener los tópicos de Kafka", e);
        }
    }
}
