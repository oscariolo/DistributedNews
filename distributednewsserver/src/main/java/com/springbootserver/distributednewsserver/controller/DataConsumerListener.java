package com.springbootserver.distributednewsserver.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.springbootserver.distributednewsserver.dto.SendNewsDto;
import com.springbootserver.distributednewsserver.service.DataToNewsProcessorService;
import com.springbootserver.distributednewsserver.service.NewsProducerService;

@Component
public class DataConsumerListener {

    @Autowired
    private DataToNewsProcessorService dataToNewsProcessorService;
    @Autowired
    private NewsProducerService newsProducerService;

    @KafkaListener(topics = "data", groupId = "default-group")
    public void listener(String data){
        try{
        SendNewsDto topic_news = dataToNewsProcessorService.processData(data).get();
        newsProducerService.sendNews(topic_news.getTopicName(), topic_news.getMessage());
        } catch (InterruptedException e) {
            // Handle the exception
            System.err.println("Error processing message: " + e.getMessage());
        } catch (ExecutionException e) {
            // Handle the exception
            System.err.println("Error processing message: " + e.getMessage());
        }
        
    }
    
}
