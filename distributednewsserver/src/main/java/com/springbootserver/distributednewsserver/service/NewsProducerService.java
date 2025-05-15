package com.springbootserver.distributednewsserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NewsProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendNews(String topic, String message) {
        // Send the news message to the specified Kafka topic
        kafkaTemplate.send(topic, message).whenComplete((result,ex)->
        {
            if(ex != null){
                System.out.println("Error sending message: " + ex.getMessage());
            }else{
                System.out.println("Message sent successfully: " + result.getRecordMetadata().offset());
            }
        });
    }
    
}
