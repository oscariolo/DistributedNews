package com.springbootserver.distributednewsserver.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.springbootserver.distributednewsserver.service.DataToNewsProcessorService;
import com.springbootserver.distributednewsserver.service.NewsProducerService;

@Component
public class DataConsumerListener {

    @Autowired
    private DataToNewsProcessorService dataToNewsProcessorService;
    @Autowired
    private NewsProducerService newsProducerService;

    // Set concurrency to 3 for concurrent processing.
    @KafkaListener(topics = "data", groupId = "default-group", concurrency = "4")
    public void listener(String data){
        try {
            dataToNewsProcessorService.processData(data)
                .thenAccept(topic_news -> {
                    newsProducerService.sendNews(topic_news.getTopicName(), topic_news.getMessage());
                })
                .exceptionally(ex -> {
                    System.err.println("Error processing message: " + ex.getMessage());
                    return null;
                });
        } catch (JsonProcessingException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Listener returns immediately while processing continues asynchronously.
    }
    
}
