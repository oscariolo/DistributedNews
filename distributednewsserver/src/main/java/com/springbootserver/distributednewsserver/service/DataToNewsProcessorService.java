package com.springbootserver.distributednewsserver.service;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.springbootserver.distributednewsserver.dto.SendNewsDto;

@Service
public class DataToNewsProcessorService {

    @Async
    public CompletableFuture<SendNewsDto> processData(String data) throws InterruptedException {
        //parse and check the data topic
        //assuming data is a JSON string
        System.out.println("Processing data: " + data);
        String topic = data.substring(0, data.indexOf(":"));
        String processedData = "";

        switch (topic) {
            case "sports":
                // Process sports data
                processedData =  processSportsData(data);
            case "politics":
                // Process politics data
                processedData = processPoliticsData(data);
            case "technology":
                // Process technology data
                processedData = processTechnologyData(data);
        }
        return CompletableFuture.completedFuture(new SendNewsDto(topic, processedData));
    }

    private String processSportsData(String data) throws InterruptedException {
        // Simulate processing time
        //Thread.sleep(1000);
        // Process the data
        
        return "Processed sports data: " + data;
    }

    private String processPoliticsData(String data) throws InterruptedException {
        // Simulate processing time
        Thread.sleep(1000);
        // Process the data
        return "Processed politics data: " + data;
    }

    private String processTechnologyData(String data) throws InterruptedException {
        // Simulate processing time
        Thread.sleep(1000);
        // Process the data
        return "Processed technology data: " + data;
    }
    
}
