package com.springbootserver.distributednewsserver.service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootserver.distributednewsserver.dto.SendNewsDto;

@Service
public class DataToNewsProcessorService {

    @Async
    public CompletableFuture<SendNewsDto> processData(String data) throws InterruptedException, JsonMappingException, JsonProcessingException {
        
        Map<String,Object> mappedData = new ObjectMapper().readValue(data, HashMap.class);
        Object topicObj = mappedData.get("topicName");
        if (topicObj == null) {
            throw new IllegalArgumentException("Missing 'topicName' in input data");
        }
        String topic = topicObj.toString();
        String processedData = "";
        
        switch (topic) {
            case "sports":
                // Process sports data
                processedData =  TextToNewsProccesors.processSportsData(mappedData);
                break;
            case "politics":
                // Process politics data
                processedData = TextToNewsProccesors.processPoliticsData(mappedData);
                break;
            case "technology":
                // Process technology data
                processedData = TextToNewsProccesors.processTechnologyData(mappedData);
                break;
            default:
                // Handle unknown topic
                processedData = TextToNewsProccesors.UnimplementedTopicData(mappedData);
                break;
        }
        return CompletableFuture.completedFuture(new SendNewsDto(topic, processedData));
    }

    
}
